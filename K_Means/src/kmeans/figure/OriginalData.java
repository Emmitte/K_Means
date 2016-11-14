package kmeans.figure;
/**
//1) Alcohol �ƾ�
//2) Malic acid ƻ����
//3) Ash     ��
// 4) Alcalinity of ash  �ҷּ��?
//5) Magnesium   þ
// 6) Total phenols   ���໯��������
//7) Flavanoids      ��ͪ
//8) Nonflavanoid phenols  �ǻ�ͪ���໯����
//9) Proanthocyanins  ������
// 10)Color intensity  ��ɫǿ��
//11)Hue              ɫ��/ɫ��
//12)OD280/OD315 of diluted wines   �ͶȾƵ�OD280/OD315
//13)Proline            ������
 * */
public class OriginalData {
	private double A1;
	private double A2;
	private double A3;
	private double A4;
	private double A5;
	private double A6;
	private double A7;
	private double A8;
	private double A9;
	private double A10;
	private double A11;
	private double A12;
	private double A13;
	private double A14;
	private int k;
	public OriginalData(){
		
	}
	public OriginalData(double A1,double A2,double A3,double A4,double A5,double A6,double A7,double A8,double A9,double A10,double A11,double A12,double A13,double A14){
		this.A1=A1;
		this.A2=A2;
		this.A3=A3;
		this.A4=A4;
		this.A5=A5;
		this.A6=A6;
		this.A7=A7;
		this.A8=A8;
		this.A9=A9;
		this.A10=A10;
		this.A11=A11;
		this.A12=A12;
		this.A13=A13;
		this.A14=A14;
	}
	public double getA1() {
		return A1;
	}
	public void setA1(double a1) {
		A1 = a1;
	}
	public double getA2() {
		return A2;
	}
	public void setA2(double a2) {
		A2 = a2;
	}
	public double getA3() {
		return A3;
	}
	public void setA3(double a3) {
		A3 = a3;
	}
	public double getA4() {
		return A4;
	}
	public void setA4(double a4) {
		A4 = a4;
	}
	public double getA5() {
		return A5;
	}
	public void setA5(double a5) {
		A5 = a5;
	}
	public double getA6() {
		return A6;
	}
	public void setA6(double a6) {
		A6 = a6;
	}
	public double getA7() {
		return A7;
	}
	public void setA7(double a7) {
		A7 = a7;
	}
	public double getA8() {
		return A8;
	}
	public void setA8(double a8) {
		A8 = a8;
	}
	public double getA9() {
		return A9;
	}
	public void setA9(double a9) {
		A9 = a9;
	}
	public double getA10() {
		return A10;
	}
	public void setA10(double a10) {
		A10 = a10;
	}
	public double getA11() {
		return A11;
	}
	public void setA11(double a11) {
		A11 = a11;
	}
	public double getA12() {
		return A12;
	}
	public void setA12(double a12) {
		A12 = a12;
	}
	public double getA13() {
		return A13;
	}
	public void setA13(double a13) {
		A13 = a13;
	}
	public double getA14() {
		return A14;
	}
	public void setA14(double a14) {
		A14 = a14;
	}
	public int getK() {
		return k;
	}
	public void setK(int k) {
		this.k = k;
	}
	public double getAi(int i){
		switch (i) {
		case 1:
			return A1;
		case 2:
			return A2;
		case 3:
			return A3;
		case 4:
			return A4;
		case 5:
			return A5;
		case 6:
			return A6;
		case 7:
			return A7;
		case 8:
			return A8;
		case 9:
			return A9;
		case 10:
			return A10;
		case 11:
			return A11;
		case 12:
			return A12;
		case 13:
			return A13;
		case 14:
			return A14;
		default:
			break;
		}
		return 0;
	}
	
	public String toString() {
		return "OriginalData [A1=" + A1 + ", A2=" + A2 + ", A3=" + A3 + ", A4="
				+ A4 + ", A5=" + A5 + ", A6=" + A6 + ", A7=" + A7 + ", A8="
				+ A8 + ", A9=" + A9 + ", A10=" + A10 + ", A11=" + A11
				+ ", A12=" + A12 + ", A13=" + A13 + ", A14=" + A14 + "]";
	}
	
}
