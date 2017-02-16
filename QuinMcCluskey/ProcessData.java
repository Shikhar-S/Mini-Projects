import java.util.*;

public class ProcessData {
	@SuppressWarnings("rawtypes")
	static HashMap final_ans=new HashMap();
	@SuppressWarnings({ "rawtypes" })
	public static void main(String args[]) throws Exception {
		TakeInput ti = new TakeInput();
		ti.input();
		boolean flag = true;
		HashMap hm = ti.hm;
		print(hm);
		while (flag) {
			HashMap temp = new HashMap();
			flag = false;
			Set set = hm.keySet();
			Iterator it = set.iterator();
			while (it.hasNext()) {
				Integer i = (Integer) it.next();
				Data ob = (Data) hm.get(i);
				Data obj = (Data) hm.get(i + 1);
				if (ob != null && obj != null) {
					boolean fl = compare(obj, ob, temp, final_ans);
					if (fl)
						flag = true;
				}
				if (ob != null && obj == null) {
					Iterator iter = ob.datum.iterator();
					while (iter.hasNext()) {
						Datum temporary = (Datum) iter.next();
						if (temporary.included == false)
							addToHashMap(final_ans, temporary);
					}
				}

			}
			if (flag)
				hm = temp;
			print(hm);
		}
		print(final_ans);

	}

	@SuppressWarnings("rawtypes")
	static void print(HashMap hm) {
		Set set = hm.keySet();
		Iterator it = set.iterator();
		while (it.hasNext()) {
			Integer i = (Integer) it.next();
			Data ob = (Data) hm.get(i);
			Iterator iter = ob.datum.iterator();
			while (iter.hasNext()) {
				Datum n = (Datum) iter.next();
				n.print();
			}

		}
		System.out.println("Finished an iteration");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	static void addToHashMap(HashMap ans, Datum x) {
		Data obj;
		int num = new TakeInput().count(x.bits);
		if (ans.containsKey(num)) {
			obj = (Data) ans.get(num);
			if (!(obj.contains(x))) {
				obj.datum.add(x);
				ans.replace(num, obj);
			}
		} else {
			obj = new Data();
			obj.datum.add(x);
			ans.put(num, obj);
		}
	}

	@SuppressWarnings({ "rawtypes" })
	static boolean compare(Data obj, Data ob, HashMap hm, HashMap final_ans) {
		boolean ans = false;
		Iterator i = ob.datum.iterator();
		while (i.hasNext()) {
			boolean temp_ans = false;
			Iterator j = obj.datum.iterator();
			Datum n = (Datum) i.next();
			while (j.hasNext()) {

				Datum m = (Datum) j.next();
				Datum obk = compareStringsAndMerge(n.bits, m.bits, n.v, m.v);
				if (obk != null) {
					n.included = true;
					m.included = true;
					addToHashMap(hm, obk);
					ans = true;
					temp_ans = true;
				}
			}

			if (temp_ans == false) {
				if (!(n.included))
					addToHashMap(final_ans, n);
			}
		}

		return ans;
	}

	@SuppressWarnings("rawtypes")
	static Datum compareStringsAndMerge(String x, String y, Vector a, Vector b) {
		Datum ob = null;
		int l = x.length();
		int c = 0, p = -1;
		for (int i = 0; i < l && c <= 1; i++) {
			if (x.charAt(i) != y.charAt(i)) {
				c++;
				p = i;
			}
		}
		if (c == 1) {
			String temp = x.substring(0, p) + "_";
			if (p != l - 1)
				temp += x.substring(p + 1, l);
			ob = new Datum(temp);
			Vector vec = merge(a, b);

			ob.v = vec;
		}
		return ob;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	static Vector merge(Vector a, Vector b) {
		Vector ans = new Vector();
		Iterator i = a.iterator();
		Iterator j = b.iterator();
		while (i.hasNext()) {
			int temp = (Integer) i.next();
			if (!(b.contains(temp))) {
				ans.add(temp);
			}
		}
		while (j.hasNext()) {
			int temp = (Integer) j.next();
			ans.add(temp);
		}
		return ans;
	}
}