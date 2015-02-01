package util;

public class Vector {

	private double[] values;
	private int size;

	public Vector(int size) {
		if (size > 0) {
			this.size = size;
			values = new double[size];
		}
	}

	public void set(int i, double value) {
		if (i >= 0 && i < size) {
			values[i] = value;
		}
	}

	public double get(int i) {
		if (i >= 0 && i < size) {
			return values[i];
		}
		return -1;
	}

	public Vector normalize() {
		double length = 0;
		for (int i = 0; i < size; i++) {
			length += Math.pow(values[i], 2);
		}
		length = Math.sqrt(length);
		for (int i = 0; i < size; i++) {
			values[i] = values[i] / length;
		}
		return this;
	}

	public Vector normalized() {
		Vector norm = (Vector) this.clone();
		double length = 0;
		for (int i = 0; i < norm.size; i++) {
			length += Math.pow(norm.values[i], 2);
		}
		length = Math.sqrt(length);
		for (int i = 0; i < norm.size; i++) {
			norm.values[i] = norm.values[i] / length;
		}
		return norm;
	}

	public double length() {
		double length = 0;
		for (int i = 0; i < size; i++) {
			length += Math.pow(values[i], 2);
		}
		length = Math.sqrt(length);
		return length;
	}

	public int getSize() {
		return size;
	}

	public Vector negative() {
		Vector neg = new Vector(size);
		for (int i = 0; i < size; i++) {
			neg.values[i] = -values[i];
		}
		return neg;
	}

	public Vector negate() {
		for (int i = 0; i < size; i++) {
			values[i] = -values[i];
		}
		return this;
	}

	public void add(Vector other) {
		if (other.size == size) {
			for (int i = 0; i < size; i++) {
				values[i] += other.values[i];
			}
		}
	}

	public double dotProduct(Vector other) {
		double sum = 0;
		if (size == other.size) {
			for (int i = 0; i < size; i++) {
				sum += values[i] * other.values[i];
			}
		}
		return sum;
	}

	public Vector scalarMultiply(double k) {
		for (int i = 0; i < size; i++) {
			values[i] *= k;
		}

		return this;
	}

	public Vector scalarMultiplied(double k) {
		Vector sm = (Vector) this.clone();
		for (int i = 0; i < sm.size; i++) {
			sm.values[i] *= k;
		}

		return sm;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		for (int i = 0; i < size - 1; i++) {
			sb.append(values[i]);
			sb.append(", ");
		}
		sb.append(values[size - 1]);
		sb.append(")");

		return sb.toString();
	}

	@Override
	public Object clone() {
		Vector v = new Vector(size);
		for (int i = 0; i < size; i++) {
			v.set(i, values[i]);
		}
		return v;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Vector) || ((Vector) other).getSize() != size) {
			return false;
		} else {
			for (int i = 0; i < size; i++) {
				if (values[i] != ((Vector) other).get(i)) {
					return false;
				}
			}
		}
		return true;
	}

}
