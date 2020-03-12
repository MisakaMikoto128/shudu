package digital;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import cell.Cell;
import foxnRabbit.SoluteShudu;
import sun.font.FontDesignMetrics;
class MyColor{
	private static ArrayList<Color> color = new ArrayList<Color>(Arrays.asList(
			new Color(0xFF,0x8C,0x00),
			new Color(0xFF,0x14,0x93),
			new Color(0xFF,0xD7,0x00),
			new Color(0xFF,0x69,0xB4),
			new Color(0xF0,0x80,0x80),
			new Color(0xFF,0x00,0x00),
			new Color(0x00,0xFF,0xFF),
			new Color(0x7C,0xFC,0x00),
			new Color(0xFF,0x45,0x00)));
	
	/**
	 * ��ȡ�洢�ĵ�i����ɫ�����û�з��غ�ɫRGB(0,0,0)
	 * @param i ��ɫ���
	 * @return ������ɫ
	 */
	public static Color getColor(int i){
		if (color.size() == 0) {
			return Color.BLACK;
		}
		return color.get(i%color.size());
		
	}
}
public class Digital implements Cell{
	
	public int i = 0;
	public boolean known = false;
	@Override
	public void draw(Graphics g, int x, int y, int size) {
		
		Font font = new Font("����",Font.BOLD,size);
		FontDesignMetrics metrics = FontDesignMetrics.getMetrics(font);
		int strWidth = metrics.stringWidth(i+"");
        int strHeight = metrics.getHeight();
        int left = (size-strWidth)/2; //���λ��
        int top = (size-strHeight)/2+metrics.getAscent(); //����λ��+�������루ԭ���������λ�ö�׼������y���굼������ƫ��ascent���룬����ascent�����Ƹպö����Ǻϣ�
        
		if (i!=0) {
			g.setFont(font);
			if (known) {
				g.setColor(new Color(0, 0, 0));
			}else {
				g.setColor(MyColor.getColor(i));
			}
			g.drawString(i+"", x+left, y + top);
			
		}
		
	}
	

    
	public static void main(String[] args) {
//		Scanner in = new Scanner(System.in);
//		String line = in.nextLine();
//		System.out.println("0x"+line.substring(1, 3)+",0x"+line.substring(3, 5)+",0x"+line.substring(5, 7));
		System.out.println(MyColor.getColor(5));
	}
	
	@Override
	public String toString() {
		return i + "";
	}
	
}
