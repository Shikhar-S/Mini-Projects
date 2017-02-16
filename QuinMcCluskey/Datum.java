/**
 * 
 */

/**
 * @author Shikhar
 *
 */
import java.util.*;

@SuppressWarnings("rawtypes")
public class Datum {
	String bits;
	boolean included;
	Vector v;

	Datum(String x) {
		bits = x;
		v = new Vector();
		included = false;
	}

	void print() {
		System.out.print(bits + " : ");
		Iterator it = v.iterator();
		while (it.hasNext())
			System.out.print(it.next() + " ");
		System.out.println();
	}

	boolean equals(Datum x) {
		boolean ans = false;
		if (x.bits.equals(this.bits) && x.v.size() == this.v.size()) {
			Iterator i = this.v.iterator();
			while (i.hasNext()) {
				Integer temp = (Integer) i.next();
				if (!(x.v.contains(temp))) {
					return false;
				}
			}
			ans = true;
		}
		return ans;
	}
}