package com.redpacket;

import java.util.Random;

public class Packet{  
    @SuppressWarnings("unused")
	private double total; // ��Ǯ��  
    private int totalVal; // ���������������Ǯ����Ϊ����  
    private int count;    // �������  
  
    public Packet(double total, int count) {  
        this.total = total;  
        this.count = count;  
        this.totalVal = (int)(total * 100); 
        System.out.println("���������");
    }  
    
    public synchronized double getEqualMoney() {
    	int money;
    	if(count !=0 && totalVal / count == 1) {  
            money = 1;  
            totalVal = totalVal - money;  
            count--;  
            return money/100.0;  
        }  
         //������� 
        if(count <= 0) {  
            money = 0;  
            
         //��ʣһ���� ֱ�ӻ�õ�ǰʣ����
        }else if(count == 1) {  
            money = totalVal;  
        }else {  
            int temp; 			//ʣ�µĽ��  
            while(true) {  
                // ���0~ƽ�����
                money = totalVal/count;  
                temp = totalVal - money;  
                // �ж����ɵĽ�����0����ʣ���Ǯ����ʣ����ƽ�ֵ�0.01Ԫ  
                if(temp*1.0/(count-1) >= 1 && money > 0) {  
                    //System.out.println("���ɽ�� ��" + money + "ʣ���� ��" + temp + "ʣ������ ��" + (count-1));  
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
        // ��֤ÿ������ͷֵ�0.01
        if(count !=0 && totalVal / count == 1) {  
            money = 1;  
            totalVal = totalVal - money;  
            count--;  
            return money/100.0;  
        }  
         //������� 
        if(count <= 0) {  
            money = 0;  
            
         //��ʣһ���� ֱ�ӻ�õ�ǰʣ����
        }else if(count == 1) {  
            money = totalVal;  
        }else {  
            int temp; 			//ʣ�µĽ��  
            while(true) {  
                // ���0~ƽ�����
                money = new Random().nextInt(totalVal/count);  
                temp = totalVal - money;  
                // �ж����ɵĽ�����0����ʣ���Ǯ����ʣ����ƽ�ֵ�0.01Ԫ  
                if(temp*1.0/(count-1) >= 1 && money > 0) {  
                    //System.out.println("���ɽ�� ��" + money + "ʣ���� ��" + temp + "ʣ������ ��" + (count-1));  
                    break;  
                }  
            }  
            totalVal = totalVal - money;  
        }  
        count--;  
        return money/100.0;  
    }  
}  