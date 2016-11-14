package kmeans.figure;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

class T implements Comparable<T>{

	private OriginalData first;
	private OriginalData second;
	private double dist;
	
	public T(OriginalData first,OriginalData second,double dist) {
		this.first = first;
		this.second = second;
		this.dist = dist;
	}
	
	public OriginalData getFirst() {
		return first;
	}

	public void setFirst(OriginalData first) {
		this.first = first;
	}

	public OriginalData getSecond() {
		return second;
	}

	public void setSecond(OriginalData second) {
		this.second = second;
	}

	public double getDist() {
		return dist;
	}

	public void setDist(double dist) {
		this.dist = dist;
	}

	public int compareTo(T o) {
		if(this.dist < o.dist){
			return -1;
		}else if (this.dist > o.dist) {
			return 1;
		}
		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(dist);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + ((second == null) ? 0 : second.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		T other = (T) obj;
		if (Double.doubleToLongBits(dist) != Double
				.doubleToLongBits(other.dist))
			return false;
		if (first == null) {
			if (other.first != null)
				return false;
		} else if (!first.equals(other.first))
			return false;
		if (second == null) {
			if (other.second != null)
				return false;
		} else if (!second.equals(other.second))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "T [first=" + first + ", second=" + second + ", dist=" + dist
				+ "]";
	}
	
}

public class Kmeans {
	public final int attributeNum = 13; // 属性总数
	public int TrainNum; // 所有训练数据的范围
	public int TestNum;
	public int numberOfData;
	int times_iteration;
	// 存放训练数据
	public ArrayList<OriginalData> dataSet = new ArrayList<OriginalData>();
	OriginalData OData = new OriginalData();
	int k;
	int count;

	public Kmeans() {
		numberOfData = 0;
	}

	public void showList(ArrayList<OriginalData> data) {
		System.out.println("dataSet:");
		for (int i = 0; i < data.size(); i++) {
			System.out.println(data.get(i).toString());
		}
	}

	// 从文件中读取数值
	public void DataRead(String path) {
		FileReader fr = null;
		BufferedReader br = null;
		String line = null;
		int num;
		/*
		 * if (path.indexOf(1) == 'r') num = TrainNum; else num = TestNum;
		 */
		num = 0;
		try {
			fr = new FileReader(path);
			br = new BufferedReader(fr);
			// line=br.readLine();
			OriginalData wine;
			ArrayList<OriginalData> data = new ArrayList<OriginalData>();
			double a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14;
			while ((line = br.readLine()) != null) {
				String strArray[] = line.split(",");
				a1 = Double.parseDouble(strArray[0]);
				a2 = Double.parseDouble(strArray[1]);
				a3 = Double.parseDouble(strArray[2]);
				a4 = Double.parseDouble(strArray[3]);
				a5 = Double.parseDouble(strArray[4]);
				a6 = Double.parseDouble(strArray[5]);
				a7 = Double.parseDouble(strArray[6]);
				a8 = Double.parseDouble(strArray[7]);
				a9 = Double.parseDouble(strArray[8]);
				a10 = Double.parseDouble(strArray[9]);
				a11 = Double.parseDouble(strArray[10]);
				a12 = Double.parseDouble(strArray[11]);
				a13 = Double.parseDouble(strArray[12]);
				a14 = Double.parseDouble(strArray[13]);
				wine = new OriginalData(a1, a2, a3, a4, a5, a6, a7, a8, a9,
						a10, a11, a12, a13, a14);
				wine.setK(0);
				data.add(wine);
				numberOfData++;
			}
			dataSet = data;
			br.readLine();
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 默认一开始将前K个元组的值作为k个簇的质心（均值）
	 * */ 
	public void initCenterPointByFirstK(OriginalData means[]) {
		for (int i = 0; i < k; i++) {
			means[i].setA2(dataSet.get(i).getA2());
			means[i].setA3(dataSet.get(i).getA3());
			means[i].setA4(dataSet.get(i).getA4());
			means[i].setA5(dataSet.get(i).getA5());
			means[i].setA6(dataSet.get(i).getA6());
			means[i].setA7(dataSet.get(i).getA7());
			means[i].setA8(dataSet.get(i).getA8());
			means[i].setA9(dataSet.get(i).getA9());
			means[i].setA10(dataSet.get(i).getA10());
			means[i].setA11(dataSet.get(i).getA11());
			means[i].setA12(dataSet.get(i).getA12());
			means[i].setA13(dataSet.get(i).getA13());
			means[i].setA14(dataSet.get(i).getA14());
		}
	}

	/**
	 * 随机化初始中心点(选用默认随机方法)
	 * */ 
	public void initCenterPointByDefaultRandom(OriginalData means[]) {
		int i, n = 0;
		List<Integer> list = new ArrayList<Integer>();
		n = dataSet.size();
		for (i = 0; i < k; i++) {
			int temp = (int) (Math.random() * n);
			while (list.contains(temp)) {
				temp = (int) (Math.random() * n);
			}
			list.add(temp);
		}
		// 随机选择K个元组的值作为k个簇的质心（均值）
		for (i = 0; i < k; i++) {
			int j = list.get(i);
			means[i].setA2(dataSet.get(j).getA2());
			means[i].setA3(dataSet.get(j).getA3());
			means[i].setA4(dataSet.get(j).getA4());
			means[i].setA5(dataSet.get(j).getA5());
			means[i].setA6(dataSet.get(j).getA6());
			means[i].setA7(dataSet.get(j).getA7());
			means[i].setA8(dataSet.get(j).getA8());
			means[i].setA9(dataSet.get(j).getA9());
			means[i].setA10(dataSet.get(j).getA10());
			means[i].setA11(dataSet.get(j).getA11());
			means[i].setA12(dataSet.get(j).getA12());
			means[i].setA13(dataSet.get(j).getA13());
			means[i].setA14(dataSet.get(j).getA14());
		}
	}

	/**
	 * 随机化初始中心点(选用高斯随机化方法)
	 * */ 
	public void initCenterPointByGaussianRandom(OriginalData means[]) {
		int i, n = 0;
		List<Integer> list = new ArrayList<Integer>();
		n = dataSet.size();
		Random random = new Random();
		for (i = 0; i < k; i++) {
			int temp = (int) (random.nextGaussian() * n);
			while (list.contains(temp) || temp >= n || temp < 0) {
				temp = (int) (random.nextGaussian() * n);
			}
			list.add(temp);
		}
		// 随机选择K个元组的值作为k个簇的质心（均值）
		for (i = 0; i < k; i++) {
			int j = list.get(i);
			means[i].setA2(dataSet.get(j).getA2());
			means[i].setA3(dataSet.get(j).getA3());
			means[i].setA4(dataSet.get(j).getA4());
			means[i].setA5(dataSet.get(j).getA5());
			means[i].setA6(dataSet.get(j).getA6());
			means[i].setA7(dataSet.get(j).getA7());
			means[i].setA8(dataSet.get(j).getA8());
			means[i].setA9(dataSet.get(j).getA9());
			means[i].setA10(dataSet.get(j).getA10());
			means[i].setA11(dataSet.get(j).getA11());
			means[i].setA12(dataSet.get(j).getA12());
			means[i].setA13(dataSet.get(j).getA13());
			means[i].setA14(dataSet.get(j).getA14());
		}
	}
	
	/**
	 * 使用Kruskal的最小生成时方法生成初始中心点
	 * 类似于层次聚类：将2个近的点合并，再不断向外扩展合并，直到仅剩余K个点
	 * */
	public void initCenterPointByMTK(OriginalData means[]) {
		OriginalData first,second = null,point = null;
		List<T> tempList;
		ArrayList<OriginalData> list = (ArrayList<OriginalData>) dataSet.clone();
		int i,j,n,flag = 0;
		double dist = 0.0,min =0.0;
		
		while(list.size() > k){
			n = list.size();
			tempList = new ArrayList<T>();
			for(i = 0;i < n-1;i++){
				first = list.get(i);
				min = getDist(first, list.get(i+1));
				second = list.get(i+1);
				for(j = i + 1;j < n;j++){
					dist = getDist(first, list.get(j));
					if(dist < min){
						min = dist;
						second = list.get(j);
					}
				}
				T t = new T(first, second, min);
				tempList.add(t);
			}
			Collections.sort(tempList);
			/*
			System.out.println("tempList:");
			for(i = 0;i < tempList.size();i++){
				System.out.println(tempList.get(i).toString());
			}
			*/
			first = tempList.get(0).getFirst();
			second = tempList.get(0).getSecond();
			double A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14;
			A1 = first.getA1();
			A2 = (first.getA2()+second.getA2())/2;
			A3 = (first.getA3()+second.getA3())/2;
			A4 = (first.getA4()+second.getA4())/2;
			A5 = (first.getA5()+second.getA5())/2;
			A6 = (first.getA6()+second.getA6())/2;
			A7 = (first.getA7()+second.getA7())/2;
			A8 = (first.getA8()+second.getA8())/2;
			A9 = (first.getA9()+second.getA9())/2;
			A10 = (first.getA10()+second.getA10())/2;
			A11 = (first.getA11()+second.getA11())/2;
			A12 = (first.getA12()+second.getA12())/2;
			A13 = (first.getA13()+second.getA13())/2;
			A14 = (first.getA14()+second.getA14())/2;
			point = new OriginalData(A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
			
			list.remove(first);
			list.remove(second);
			list.add(point);
		}
		for(i = 0;i < list.size();i++){
			means[i] = list.get(i);
		}
		
	}
	
	public void execute(int m_k) {
		k = m_k;
		ArrayList clusters[] = new ArrayList[k];
		OriginalData means[] = new OriginalData[k];
		ArrayList dists[] = new ArrayList[k];
		Map locs[] = new HashMap[k];
		int i = 0;
		int time = 0;
		
		for (i = 0; i < k; i++) {
			clusters[i] = new ArrayList<OriginalData>();
			means[i] = new OriginalData();
			dists[i] = new ArrayList<Double>();
			locs[i] = new HashMap<Double, Integer>();
		}
		
		//initCenterPointByFirstK(means);
		//initCenterPointByDefaultRandom(means);
		//initCenterPointByGaussianRandom(means);
		initCenterPointByMTK(means);
		System.out.println("初始质心:");
		for(i = 0;i < k;i++){
			System.out.println(means[i].toString());
		}
		int lable = 0;
		// 根据默认的质心给簇赋值
		for (i = 0; i < dataSet.size(); i++) {
			lable = clusterOfTuple(means, dataSet.get(i));
			clusters[lable].add(dataSet.get(i));
		}
		// 输出刚开始的簇
		for (lable = 0; lable < k; lable++) {
			System.out.println("第" + (lable + 1) + "个簇：");
			ArrayList<OriginalData> t = clusters[lable];
			showList(t);
			System.out.println();
		}
		double oldVar = -1;
		double newVar = getVar(clusters, means, dists, locs);
		while (Math.abs(newVar - oldVar) >= 1 && time < times_iteration) // 当新旧函数值相差不到1即准则函数值不发生明显变化时，算法终止
		{
			time++;
			for (i = 0; i < k; i++) // 更新每个簇的中心点
			{
				means[i]=getMeans_ByCore(clusters[i],dists[i],locs[i]);
				//means[i] = getMeans_ByAvg(clusters[i], dists[i], locs[i]);
				System.out.println("new means[i]=" + means[i].toString());
				dists[i].clear();
				locs[i].clear();
			}
			oldVar = newVar;
			newVar = getVar(clusters, means, dists, locs); // 计算新的准则函数值
			for (i = 0; i < k; i++) {
				clusters[i].clear(); // 清空每个簇
				dists[i].clear();
				locs[i].clear();
			}
			double d;
			// 根据新的质心获得新的簇
			for (i = 0; i < dataSet.size(); i++) {
				lable = clusterOfTuple(means, dataSet.get(i));
				clusters[lable].add(dataSet.get(i));
				d = getDist(means[lable], dataSet.get(i));
				dists[lable].add(d);
				locs[lable].put(d, i);
			}
			// 输出当前的簇
			count = 0;
			for (lable = 0; lable < k; lable++) {
				System.out.println("第" + (lable + 1) + "个簇：");
				ArrayList<OriginalData> t = clusters[lable];
				for (int j = 0; j < t.size(); j++) {
					if (t.get(j).getA1() == (lable + 1))
						count++;
					System.out.println(t.get(j).toString());
				}
				System.out.println();
			}
		}
	}

	// 根据质心，决定当前元组属于哪个簇
	public int clusterOfTuple(OriginalData means[], OriginalData tuple) {
		double dist = getDist(means[0], tuple);
		double tmp;
		int lable = 0; // 标示属于哪一个簇
		for (int i = 1; i < k; i++) {
			tmp = getDist(means[i], tuple);
			if (tmp < dist) {
				dist = tmp;
				lable = i;
			}
		}
		return lable;
	}

	public double getDist(OriginalData mean, OriginalData data) {
		return (mean.getA2() - data.getA2()) * (mean.getA2() - data.getA2())
				+ (mean.getA3() - data.getA3()) * (mean.getA3() - data.getA3())
				+ (mean.getA4() - data.getA4()) * (mean.getA4() - data.getA4())
				+ (mean.getA5() - data.getA5()) * (mean.getA5() - data.getA5())
				+ (mean.getA6() - data.getA6()) * (mean.getA6() - data.getA6())
				+ (mean.getA7() - data.getA7()) * (mean.getA7() - data.getA7())
				+ (mean.getA8() - data.getA8()) * (mean.getA8() - data.getA8())
				+ (mean.getA9() - data.getA9()) * (mean.getA9() - data.getA9())
				+ (mean.getA10() - data.getA10())
				* (mean.getA10() - data.getA10())
				+ (mean.getA11() - data.getA11())
				* (mean.getA11() - data.getA11())
				+ (mean.getA12() - data.getA12())
				* (mean.getA12() - data.getA12())
				+ (mean.getA13() - data.getA13())
				* (mean.getA13() - data.getA13())
				+ (mean.getA14() - data.getA14())
				* (mean.getA14() - data.getA14());
	}

	// 获得给定簇集的平方误差
	public double getVar(ArrayList<OriginalData> clusters[],
			OriginalData means[], ArrayList<Double> dists[],
			Map<Double, Integer> locs[]) {
		double var = 0;
		double d = 0;
		for (int i = 0; i < k; i++) {
			ArrayList<OriginalData> t = clusters[i];
			for (int j = 0; j < t.size(); j++) {
				d = getDist(t.get(j), means[i]);
				var += d;
				dists[i].add(d);
				locs[i].put(d, j);
			}
		}
		return var;
	}
	/**
	 * 根据平均值取中心点
	 * */
	public OriginalData getMeans_ByAvg(ArrayList<OriginalData> cluster,
			ArrayList<Double> dist, Map<Double, Integer> loc) {
		int num = cluster.size();
		OriginalData t = new OriginalData();

		double A2 = 0, A3 = 0, A4 = 0, A5 = 0, A6 = 0, A7 = 0, A8 = 0, A9 = 0, A10 = 0, A11 = 0, A12 = 0, A13 = 0, A14 = 0;

		for (int i = 0; i < num; i++) {
			A2 += cluster.get(i).getA2();
			A3 += cluster.get(i).getA3();
			A4 += cluster.get(i).getA4();
			A5 += cluster.get(i).getA5();
			A6 += cluster.get(i).getA6();
			A7 += cluster.get(i).getA7();
			A8 += cluster.get(i).getA8();
			A9 += cluster.get(i).getA9();
			A10 += cluster.get(i).getA10();
			A11 += cluster.get(i).getA11();
			A12 += cluster.get(i).getA12();
			A13 += cluster.get(i).getA13();
			A14 += cluster.get(i).getA14();
		}
		t.setA2(A2 / num);
		t.setA3(A3 / num);
		t.setA4(A4 / num);
		t.setA5(A5 / num);
		t.setA6(A6 / num);
		t.setA7(A7 / num);
		t.setA8(A8 / num);
		t.setA9(A9 / num);
		t.setA10(A10 / num);
		t.setA11(A11 / num);
		t.setA12(A12 / num);
		t.setA13(A13 / num);
		t.setA14(A14 / num);

		return t;
	}
	/**
	 * 根据重心取中心点
	 * */
	public OriginalData getMeans_ByCore(ArrayList<OriginalData> cluster,
			ArrayList<Double> dist, Map<Double, Integer> loc) {
		int num = cluster.size();
		OriginalData t = new OriginalData();
		System.out.println("原dist：" + dist);
		Collections.sort(dist);
		System.out.println("现dist:" + dist);
		// System.exit(0);
		double d = dist.get(dist.size() / 2);
		int i = (int) loc.get(d);
		System.out.println("dist.n=" + dist.size() + " loc.n=" + loc.size()
				+ " cluster.n=" + cluster.size() + " i=" + i);
		t = dataSet.get(i);
		// t=cluster.get(i);
		// t=cluster.get(num/2);

		return t;
	}

	public double getCorrect() {
		double correct = count * 1.0 / numberOfData * 100;
		return correct;
	}

	public int getTimes_iteration() {
		return times_iteration;
	}

	public void setTimes_iteration(int times_iteration) {
		this.times_iteration = times_iteration;
	}

}
