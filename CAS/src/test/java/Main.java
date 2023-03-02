import java.util.ArrayList;
import java.util.Arrays;

/**
 * Copyright (c) by [安徽航天信息]
 *
 * @author LvLu
 * @className Main
 * @date 2023-02-27 14:20
 * @description
 */
public class Main {
	public static void main(String[] args) {
		int[] arr = {1,1,2,2,3,17,18,19,19,20,20,20};
		//标记
		int[] state = new int[arr.length];
		//所需的和值
		int target = 20;
		//结果集
		ArrayList<ArrayList<Integer>> res = new ArrayList<>();
		ArrayList<Integer> ans = new ArrayList<>();
		//排序
		Arrays.sort(arr);
		for(int i = 0;i < arr.length;i++){
			if(state[i] == 1)continue;
			getNumByTotal(arr,0,target,i,res,ans,state);
		}
		System.out.println("=====输出结果=====");
		System.out.println("=====结果数量为：" + res.size());
		System.out.println("======组合如下：");
		for(int i = 0;i < res.size();i++){
			ArrayList<Integer> list = res.get(i);
			for(int j = 0;j < list.size();j++){
				System.out.print(list.get(j) + " ");
			}
			System.out.println();
		}

	}
	public static int getNumByTotal(int[] q, int sum, int target, int index, ArrayList<ArrayList<Integer>> res,ArrayList<Integer> ans,int[] state){
		//剪枝
		if(sum > target)return 0;
		//拿到结果集
		if(sum == target){
			ArrayList<Integer> t = new ArrayList<>(ans);
			res.add(t);
			return 1;
		}
		for(int i = index;i < q.length;i++){
			int tmp = q[i] + sum;
			if(tmp <= target && state[i] == 0){
				ans.add(q[i]);
				state[i] = 1;
				if(getNumByTotal(q,tmp,target,i + 1,res,ans,state) == 1){
					//找到了，该分支全部结束
					ans.remove(ans.size() - 1);
					return 1;
				}
				state[i] = 0;
				ans.remove(ans.size() - 1);
			}
		}
		return 0;
	}
}
