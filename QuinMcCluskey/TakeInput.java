import java.util.*;

public class TakeInput {
	@SuppressWarnings("rawtypes")
	HashMap hm = new HashMap();
	int n, m;

	public void input() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Number of Minterms");
		n = sc.nextInt();
		System.out.println("Enter number of Variables");
		m = sc.nextInt();
		System.out.println("Enter " + n + " minterms.");
		int arr[] = new int[n];

		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}

		for (int i = 0; i < n; i++) {
			String x = Integer.toBinaryString(arr[i]);
			while (x.length() < m)
				x = "0" + x;
			insert(x, arr[i]);
		}
		sc.close();
	}

	@SuppressWarnings({ "unchecked" })
	public void insert(String x, int num) {
		int c = count(x);
		Datum line = new Datum(x);
		line.v.add(num);
		if (hm.containsKey(c)) {
			Data ob = (Data) hm.get(c);
			ob.datum.add(line);
			hm.replace(c, ob);
		} else {
			Data ob = new Data();
			ob.datum.add(line);
			hm.put(c, ob);
		}
	}

	public int count(String x) {
		int ans = 0;
		int l = x.length();
		for (int i = 0; i < l; i++)
			if (x.charAt(i) == '1')
				ans++;
		return ans;
	}
}