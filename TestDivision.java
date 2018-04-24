package direct501;

import java.text.DecimalFormat;

public class TestDivision {
	public static void main(String[] args) {
		String ax="11";
		String bx="12";
		DecimalFormat df = new DecimalFormat("0.00");
		String str = df.format(Float.parseFloat(ax)/Float.parseFloat(bx)*100)+"%";
		System.out.println(str);
	}

}
