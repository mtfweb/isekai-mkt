package com.bookshop01.order.vo;

import java.sql.Date;

public class OrderVO {
    private int order_seq_num;       // 주문상품 일련번호 (PK)
    private int order_id;            // 주문 번호
    private String member_id;        // 주문자 아이디
    private int goods_id;            // 상품 번호

    private String orderer_name;     // 주문자 이름
    private String goods_title;      // 상품명
    private int order_goods_qty;     // 수량
    private int goods_sales_price;   // 판매가

    private String receiver_name;    // 수령자 이름
    private String receiver_hp1;     // 수령자 연락처1
    private String receiver_hp2;     // 수령자 연락처2
    private String receiver_hp3;     // 수령자 연락처3

    private String delivery_address; // 배송주소
    private String delivery_state;   // 배송상태
    private String pay_method;       // 결제방법
    private Date pay_order_time;     // 결제일시

    public int getOrder_seq_num() { return order_seq_num; }
    public void setOrder_seq_num(int order_seq_num) { this.order_seq_num = order_seq_num; }

    public int getOrder_id() { return order_id; }
    public void setOrder_id(int order_id) { this.order_id = order_id; }

    public String getMember_id() { return member_id; }
    public void setMember_id(String member_id) { this.member_id = member_id; }

    public int getGoods_id() { return goods_id; }
    public void setGoods_id(int goods_id) { this.goods_id = goods_id; }

    public String getOrderer_name() { return orderer_name; }
    public void setOrderer_name(String orderer_name) { this.orderer_name = orderer_name; }

    public String getGoods_title() { return goods_title; }
    public void setGoods_title(String goods_title) { this.goods_title = goods_title; }

    public int getOrder_goods_qty() { return order_goods_qty; }
    public void setOrder_goods_qty(int order_goods_qty) { this.order_goods_qty = order_goods_qty; }

    public int getGoods_sales_price() { return goods_sales_price; }
    public void setGoods_sales_price(int goods_sales_price) { this.goods_sales_price = goods_sales_price; }

    public String getReceiver_name() { return receiver_name; }
    public void setReceiver_name(String receiver_name) { this.receiver_name = receiver_name; }

    public String getReceiver_hp1() { return receiver_hp1; }
    public void setReceiver_hp1(String receiver_hp1) { this.receiver_hp1 = receiver_hp1; }

    public String getReceiver_hp2() { return receiver_hp2; }
    public void setReceiver_hp2(String receiver_hp2) { this.receiver_hp2 = receiver_hp2; }

    public String getReceiver_hp3() { return receiver_hp3; }
    public void setReceiver_hp3(String receiver_hp3) { this.receiver_hp3 = receiver_hp3; }

    public String getDelivery_address() { return delivery_address; }
    public void setDelivery_address(String delivery_address) { this.delivery_address = delivery_address; }

    public String getDelivery_state() { return delivery_state; }
    public void setDelivery_state(String delivery_state) { this.delivery_state = delivery_state; }

    public String getPay_method() { return pay_method; }
    public void setPay_method(String pay_method) { this.pay_method = pay_method; }

    public Date getPay_order_time() { return pay_order_time; }
    public void setPay_order_time(Date pay_order_time) { this.pay_order_time = pay_order_time; }
}
