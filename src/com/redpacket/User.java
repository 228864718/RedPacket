package com.redpacket;

public class User implements Runnable{  
    private Packet bao;  
    private int m;
    public User(int m,Packet bao) {  
        this.bao = bao; 
        this.m = m;
    }  
    @Override  
    public void run() { 
    	double money = 0;
    	if(m == 0) {
    		money = bao.getRandomMoney();  
    	}
    	if(m == 1) {
    		money = bao.getEqualMoney();  
    	}
        if(money == 0) {  
            System.out.println(Thread.currentThread().getName() + "不好意思，您手慢了！");  
        }else {  
            System.out.println(Thread.currentThread().getName() + "抢到 " + money + "元");  
        }  
          
    }  
}  
  