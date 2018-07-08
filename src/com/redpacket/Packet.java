package com.redpacket;

import java.util.Random;

public class Packet{  
    @SuppressWarnings("unused")
	private double total; // 总钱数  
    private int totalVal; // 随机生成整数，将钱数化为整数  
    private int count;    // 红包总数  
  
    public Packet(double total, int count) {  
        this.total = total;  
        this.count = count;  
        this.totalVal = (int)(total * 100); 
        System.out.println("红包来啦！");
    }  
    
    public synchronized double getEqualMoney() {
    	int money;
    	if(count !=0 && totalVal / count == 1) {  
            money = 1;  
            totalVal = totalVal - money;  
            count--;  
            return money/100.0;  
        }  
         //红包抢完 
        if(count <= 0) {  
            money = 0;  
            
         //仅剩一个包 直接获得当前剩余金额
        }else if(count == 1) {  
            money = totalVal;  
        }else {  
            int temp; 			//剩下的金额  
            while(true) {  
                // 随机0~平均金额
                money = totalVal/count;  
                temp = totalVal - money;  
                // 判断生成的金额大于0，且剩余的钱数够剩下人平分到0.01元  
                if(temp*1.0/(count-1) >= 1 && money > 0) {  
                    //System.out.println("生成金额 ：" + money + "剩余金额 ：" + temp + "剩余人数 ：" + (count-1));  
                    break;  
                }  
            }  
            totalVal = totalVal - money;  
        }  
        count--;  
        return money/100.0;   
    	
    }
    public synchronized double getRandomMoney() {  
        int money;  
        // 保证每个人最低分得0.01
        if(count !=0 && totalVal / count == 1) {  
            money = 1;  
            totalVal = totalVal - money;  
            count--;  
            return money/100.0;  
        }  
         //红包抢完 
        if(count <= 0) {  
            money = 0;  
            
         //仅剩一个包 直接获得当前剩余金额
        }else if(count == 1) {  
            money = totalVal;  
        }else {  
            int temp; 			//剩下的金额  
            while(true) {  
                // 随机0~平均金额
                money = new Random().nextInt(totalVal/count);  
                temp = totalVal - money;  
                // 判断生成的金额大于0，且剩余的钱数够剩下人平分到0.01元  
                if(temp*1.0/(count-1) >= 1 && money > 0) {  
                    //System.out.println("生成金额 ：" + money + "剩余金额 ：" + temp + "剩余人数 ：" + (count-1));  
                    break;  
                }  
            }  
            totalVal = totalVal - money;  
        }  
        count--;  
        return money/100.0;  
    }  
}  