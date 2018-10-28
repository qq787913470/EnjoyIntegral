package com.yada.enjoyIntegral.util;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.yada.enjoyIntegral.model.ProductCart;


public class PageUtil {

    public static void writePage(PrintWriter writer, String name, String certificateNo, String phone, String certificateType, List<ProductCart> productCarts, String userId, String userName, String orgName) throws SQLException, UnsupportedEncodingException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = format.format(new Date());
        Double totalIntegralValue = 0.0;
        writer.println("<div class='top' style='width=100%;'><img src='../images/logo.jpg' /><p style='float:right; margin-top:70px; margin-bottom:0;'>" + orgName + "  " + userName + userId + "</p></div>");
        writer.println("<h1>积分兑换确认单</h1>");
        if (hasNomalProduct(productCarts)) {
            writer.println("<h3>您本次尊享积分兑换的服务如下：</h3>");
            writer.println("<table>");
            int nomalCount = 1;
            for (int i = 0; i < productCarts.size(); i++) {
                if (productCarts.get(i).getActivityName().equals("nomal")) {
                    writer.println("<tr>");
                    writer.println("<td>序号</td>");
                    writer.println("<td>项目</td>");
                    writer.println("<td>单价</td>");
                    writer.println("<td>兑换数量（份）</td>");
                    writer.println("<td>合计积分数</td>");
                    writer.println("</tr>");
                    writer.println("<tr>");
                    writer.println("<td>" + (nomalCount++) + "</td>");
                    writer.println("<td>" + productCarts.get(i).getProductName() + "</td>");
                    writer.println("<td>" + productCarts.get(i).getPrice() + "</td>");
                    writer.println("<td>" + productCarts.get(i).getCount() + "</td>");
                    writer.println("<td>" + productCarts.get(i).getIntegralValue() + "</td>");
                    writer.println("</tr>");
                    writer.println("<tr>");
                    writer.println("<td class='tfoot' colspan='5'>使用说明:" + getProductDetal(productCarts.get(i)) + "</td>");
                    writer.println("</tr>");
                    totalIntegralValue = totalIntegralValue + productCarts.get(i).getIntegralValue();
                    productCarts.set(i, null);
                }
            }
            writer.println("</table>");
            writer.println("<p></p>");
            writer.println("<div class='line'></div>");
        }
        removeAllNullElements(productCarts);
        if (productCarts.size() != 0)
            writer.println("<h3>您本次参与的活动信息如下：</h3>");
        while (productCarts.size() != 0) {
            int activityCount = 1;
            String activityName = productCarts.get(0).getActivityName();
            String activityRemark = productCarts.get(0).getActivityRemark();
            writer.println("<h3>活动名称：" + activityName + "</h3>");
            writer.println("<table>");
            writer.println("<tr>");
            writer.println("<td>序号</td>");
            writer.println("<td>项目</td>");
            writer.println("<td>单价</td>");
            writer.println("<td>兑换数量（份）</td>");
            writer.println("<td>合计积分数</td>");
            writer.println("</tr>");
            writer.println("<tr>");
            writer.println("<td>" + activityCount + "</td>");
            writer.println("<td>" + productCarts.get(0).getProductName() + "</td>");
            writer.println("<td>" + productCarts.get(0).getPrice() + "</td>");
            writer.println("<td>" + productCarts.get(0).getCount() + "</td>");
            writer.println("<td>" + productCarts.get(0).getIntegralValue() + "</td>");
            writer.println("</tr>");
            writer.println("<tr>");
            writer.println("<td class='tfoot' colspan='5'>使用说明:" + getProductDetal(productCarts.get(0)) + "</td>");
            writer.println("</tr>");
            totalIntegralValue = totalIntegralValue + productCarts.get(0).getIntegralValue();
            activityCount++;
            productCarts.remove(0);
            for (int i = 0; i < productCarts.size(); ) {
                if (productCarts.get(i).getActivityName().equals(activityName)) {
                    writer.println("<tr>");
                    writer.println("<td>序号</td>");
                    writer.println("<td>项目</td>");
                    writer.println("<td>单价</td>");
                    writer.println("<td>兑换数量（份）</td>");
                    writer.println("<td>合计积分数</td>");
                    writer.println("</tr>");
                    writer.println("<tr>");
                    writer.println("<td>" + activityCount + "</td>");
                    writer.println("<td>" + productCarts.get(i).getProductName() + "</td>");
                    writer.println("<td>" + productCarts.get(i).getPrice() + "</td>");
                    writer.println("<td>" + productCarts.get(i).getCount() + "</td>");
                    writer.println("<td>" + productCarts.get(i).getIntegralValue() + "</td>");
                    writer.println("</tr>");
                    writer.println("<tr>");
                    writer.println("<td class='tfoot' colspan='5'>使用说明:" + getProductDetal(productCarts.get(i)) + "</td>");
                    writer.println("</tr>");
                    totalIntegralValue = totalIntegralValue + productCarts.get(i).getIntegralValue();
                    productCarts.remove(i);
                    activityCount++;
                } else {
                    i++;
                }
            }
            writer.println("</table>");
            writer.println("<p></p>");
            writer.println("<p>活动规则：<br />" + activityRemark + "</p>");
            writer.println("<div class='line'></div>        ");
        }
        /*writer.println("<p class='bit'>服务供应商将于次月10日前将服务兑换码通过短信形式发送至您的手机，请注意查收。</p>");*/
        writer.println("<p class='bit'>以此确认单为服务凭证,复印无效。</p>");
        writer.println("<p class='bit'>您本次兑换共需要尊享积分：" + totalIntegralValue + "分。</p>");
        writer.println("<p class='bit'>您本次兑换所预留的相关信息如下：</p>");
        writer.println("<table class='tab'>");
        writer.println("<tr>");
        writer.println("<td>姓名</td>");
		/*writer.println("<td>证件号</td>");*/
        writer.println("<td>接收服务手机号</td>");
        writer.println("<td>兑换时间</td>");
        writer.println("</tr>");
        writer.println("<tr>");
        writer.println("<td>" + name + "</td>");
	/*	writer.println("<td>"+certificateNo+"</td>");*/
        writer.println("<td>" + phone + "</td>");
        writer.println("<td>" + dateStr + "</td>");
        writer.println("</tr>");
        writer.println("</table>        ");
        writer.println("<p class='bit'>请您核对以上信息无误并再次明确服务使用规则后，签字确认。</p>");
        writer.println("<p class='write'>客户签字：___________</p>");
        writer.println("<div class='nothing'></div>");
        writer.print("%" + totalIntegralValue);
    }

    public static void writePageNew(PrintWriter writer, String name, String certificateNo, String phone, String certificateType, List<ProductCart> productCarts) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = format.format(new Date());
        Double totalIntegralValue = 0.0;
        writer.println("<h1>积分兑换确认单</h1>");
        if (hasNomalProduct(productCarts)) {
            writer.println("<p class='bit'>您本次兑换的相关信息如下：</p>");
            writer.println("<table class='tab'>");
            writer.println("<tr>");
            writer.println("<td >姓名</td>");
            writer.println("<td >兑换时间</td>");
            writer.println("<td>接收服务手机号</td>");
            writer.println("<td>兑换积分</td>");
            writer.println("<td>服务供应商</td>");
            writer.println("<td>兑换项目</td>");
            writer.println("<td>兑换份数</td>");
            writer.println("</tr>");
            writer.println("<tr>");
            writer.println("<td rowspan='" + productCarts.size() + 1 + "'>" + name + "</td>");
            writer.println("<td rowspan='" + productCarts.size() + 1 + "'>" + dateStr + "</td>");
            writer.println("<td rowspan='" + productCarts.size() + 1 + "'>" + phone + "</td>");
            Map<String, String> merMessMap = new HashMap<>();
            for (int i = 0; i < productCarts.size(); i++) {

                merMessMap.put(productCarts.get(i).getMerName(), productCarts.get(i).getMerDetail());
                writer.println("<tr>");
                writer.println("<td>" + productCarts.get(i).getIntegralValue() + "</td>");
                writer.println("<td>" + productCarts.get(i).getMerName() + "</td>");
                writer.println("<td>" + productCarts.get(i).getProductName() + "</td>");
                writer.println("<td>" + productCarts.get(i).getCount() + "</td>");
                writer.println("</tr>");
            }

            writer.println("</tr>");
            writer.println("</table>");
            writer.println("<p class='bit'>您本次兑换所需要尊享积分共计：95分</p> ");
            writer.println("<p class='bit'>相关说明：</p>");
            writer.println("<p class='bit'>1、积分仅限兑换当日撤销冲正。</p>  ");
            writer.println("<p class='bit'>2、服务确认有效期为兑换日后90天，请尽快使用。</p>");
            writer.println("<p class='bit'>3、兑换服务将于3个工作日后生效。</p>");
            writer.println("<p class='bit'>4、兑换保险卡一般将于30天后可激活使用，具体时间以中银保险服务人员通知为准。</p>");
            writer.println("<p class='bit'></p>  ");

            Iterator iter = merMessMap.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                writer.println("<p class='bit'>"+entry.getKey()+":"+entry.getValue()+"</p>  ");
            }

            writer.println("<p class='bit'></p>  ");
            writer.println("<p class='bit'>请您核对以上信息无误并再次明确服务使用规则后，签字确认。</p>");
            writer.println("<p class='write'>客户签字：___________</p>");
            writer.println("<div class='nothing'></div>");


        }
    }

    private static String getProductDetal(ProductCart productCart) throws SQLException, UnsupportedEncodingException {
        String detail = "";
        if (productCart.getProductDetail() != null && !productCart.getProductDetailString().equals("")) {
            detail = productCart.getProductDetailString();
        } else {
            detail = productCart.getProductRemark();
        }

        return detail;
    }

    private static boolean hasNomalProduct(List<ProductCart> productCarts) {
        for (int i = 0; i < productCarts.size(); i++) {
            if (productCarts.get(i).getActivityName().equals("nomal")) {
                return true;
            }
        }
        return false;
    }

    private static void removeAllNullElements(List<ProductCart> productCarts) {
        List<ProductCart> nullArr = new ArrayList<ProductCart>();
        nullArr.add(null);
        productCarts.removeAll(nullArr);
    }
}
