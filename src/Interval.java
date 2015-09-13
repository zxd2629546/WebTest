public class Interval{
	int st, ed;

	public Interval(int st, int ed) {
		this.st = st;
		this.ed = ed;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return st + "-" + ed;
	}
	
	public int length(){
		return ed - st + 1;
	}
	
	@Override
	public boolean equals(Object arg0) {
		Interval b = (Interval)arg0;
		return st == b.st && ed == b.ed;
	}
	
	@Override
	public int hashCode() {
		return (st << 4) | ed;
	}
}