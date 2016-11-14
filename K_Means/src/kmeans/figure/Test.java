package kmeans.figure;

public class Test {

	public static void main(String[] args) {
		Kmeans kmeans=new Kmeans();
		String path="dataset/winedata/winedata.dat";
		double start,end,dur;
		kmeans.setTimes_iteration(50);
		kmeans.DataRead(path);
		start=System.currentTimeMillis();
		kmeans.execute(3);
		System.out.println("correct rate:"+kmeans.getCorrect()+"%");
		end=System.currentTimeMillis();
		dur=end-start;
		System.out.println("running time:"+dur);
	}

}
