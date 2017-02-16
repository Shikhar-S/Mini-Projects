import java.util.*;

@SuppressWarnings("rawtypes")
public class Data {
	Vector datum;

	Data() {
		datum = new Vector();
	}

	boolean contains(Datum x) {
		boolean ans = false;
		Iterator i = this.datum.iterator();
		while (i.hasNext()) {
			Datum y = (Datum) i.next();
			if (y.equals(x)) {
				ans = true;
				return ans;
			}
		}
		return ans;

	}
}