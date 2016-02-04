package com.kodcu.examples.dunya.madde109.kodcu.questions;


import java.util.HashMap;
import java.util.Map;

public final class TelefonRehberi {
	private final short alanKod;
	private final short prefix;
	private final short numara;

	public TelefonRehberi(int alanKod, int prefix, int numara) {
		numaraKontrol(alanKod, 999, "alan kod");
		numaraKontrol(prefix, 999, "prefix");
		numaraKontrol(numara, 9999, "numara");
		this.alanKod = (short) alanKod;
		this.prefix = (short) prefix;
		this.numara = (short) numara;
	}

	private static void numaraKontrol(int arg, int max, String name) {
		if (arg < 0 || arg > max)
			throw new IllegalArgumentException(name + ": " + arg);
	}

	@Override
	public int hashCode() { // hashcode override edildi. aynı objeler aynı hashcodelar ile tutuluyor.hashmap vs hashtable
		int result = 17;
		result = 31 * result + alanKod;
		result = 31 * result + prefix;
		result = 31 * result + numara;
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof TelefonRehberi))
			return false;
		TelefonRehberi pn = (TelefonRehberi) o;
		return pn.numara == numara && pn.prefix == prefix
				&& pn.alanKod == alanKod;
	}



	public static void main(String[] args) {
		Map<TelefonRehberi, String> m = new HashMap<TelefonRehberi, String>();
		m.put(new TelefonRehberi(707, 867, 5309), "Ayse");
		System.out.println(m.get(new TelefonRehberi(707, 867, 5309))); // farklı referansla karşılaştırılıyor. dolayısıyla hashcode kullanaması lazım aynı obje için aynı hashcode
	}
}
