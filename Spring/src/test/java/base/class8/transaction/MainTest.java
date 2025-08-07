package base.class8.transaction;

import base.class8.transaction.config.MainConfig;
import base.class8.transaction.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 *
 * @author LvLu
 * @className MainTest
 * @date 2024-02-20 16:38
 * @description
 */
public class MainTest {

	@Test
	public void transaction(){
		TestInfo a = new TestInfo("a","b","c","d",1,1);
		TestInfo b = new TestInfo("a","b","c","e",1,2);
		TestInfo c = new TestInfo("a","b","f","f",1,3);
		TestInfo d = new TestInfo("a","b","f","g",1,4);
		TestInfo e = new TestInfo("a","dwq","f","g",1,4);

		List<TestInfo> data = new ArrayList<>();
		data.add(a);
		data.add(b);
//		data.add(c);
//		data.add(d);
		data.add(e);

		List<TestInfo> res = new ArrayList<>();
		List<String> key = new ArrayList<>();
		key.add("a");
		key.add("b");
		key.add("c");
		key.add("d");

		List<TestInfo> dfs = dfs(data, res, key, 0);
		for (TestInfo re : dfs) {
			System.out.printf("%8s %8s %8s %8s %8s %8s\n",re.getA(),re.getB(),re.getC(),re.getD(),re.getMoneyA(),re.getMoneyB());
		}
	}

	private List<TestInfo> dfs(List<TestInfo> data,List<TestInfo> res, List<String> key,int index) {

		List<TestInfo> ans = new ArrayList<>();

		if(index >= key.size()){
			ans.addAll(data);
			return ans;
		}

		Map<String, List<TestInfo>> listMap = data.stream().collect(Collectors.groupingBy(x -> this.getOrderKey(x, key, index)));

		Set<Map.Entry<String, List<TestInfo>>> entries = listMap.entrySet();

		for (Map.Entry<String, List<TestInfo>> entry : entries) {
			List<TestInfo> value = entry.getValue();
			ans.addAll(dfs(value,res,key,index + 1));
		}

		if(listMap.size() >= 2){
			int totalA = 0;
			int totalB = 0;
			//添加合计
			for (TestInfo datum : data) {
				totalA += datum.getMoneyA();
				totalB += datum.getMoneyB();
			}

			TestInfo hj = new TestInfo();
			if(index == 0){
				hj.setA("合计");
				hj.setMoneyA(totalA);
				hj.setMoneyB(totalB);
			}else if(index == 1){
				hj.setB("合计");
				hj.setMoneyA(totalA);
				hj.setMoneyB(totalB);
			}else if(index == 2){
				hj.setC("合计");
				hj.setMoneyA(totalA);
				hj.setMoneyB(totalB);
			}else if(index == 3){
				hj.setD("合计");
				hj.setMoneyA(totalA);
				hj.setMoneyB(totalB);
			}
			ans.add(hj);
		}


		return ans;
	}

	private String getOrderKey(TestInfo x,List<String > key,int index) {
		String res = "";
		for(int i = 0;i <= index;i++){
			if(i == 0){
				res += x.getA();
			}else if(i == 1){
				res += x.getB();
			}else if(i == 2){
				res += x.getC();
			}else if(i == 3){
				res += x.getD();
			}
		}
		return res;
	}


}

@Data
@AllArgsConstructor
@NoArgsConstructor
class TestInfo{

	//自定义字段a
	private String a;

	//自定义字段b
	private String b;

	//自定义字段c
	private String c;

	//自定义字段d
	private String d;

	//钱a
	private int moneyA;

	//钱b
	private int moneyB;

}
