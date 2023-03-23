import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
	public static void main(String[] args) throws IOException {
		File file = new File("D:\\test2\\150301199811285326\\2023-03-16\\1510202303_11609306_安徽精检分析测试有限公司.pdf");
		FileInputStream in = new FileInputStream(file);
		ByteArrayOutputStream ps = new ByteArrayOutputStream();
		byte[] load = new byte[1024];
		int len = 1;
		while((len = in.read(load)) != -1){
			ps.write(load,0,len);
		}
		in.close();
		ps.close();
		System.out.println(Arrays.toString(ps.toByteArray()));
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
