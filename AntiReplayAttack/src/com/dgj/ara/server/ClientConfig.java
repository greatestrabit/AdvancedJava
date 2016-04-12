package com.dgj.ara.server;

import java.util.Random;
import java.util.Vector;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class ClientConfig {
	private final String algrithm;
	private final String secretKey;
	private Mac mac;
	private final Vector<Integer> validNumbers = new Vector<Integer>();

	public ClientConfig(final String secrectKey, final String algrithm) {
		this.algrithm = algrithm;
		this.secretKey = secrectKey;

		try {
			SecretKeySpec keySpec = new SecretKeySpec(secrectKey.getBytes(), algrithm);
			this.mac = Mac.getInstance(algrithm);
			mac.init(keySpec);
		} catch (Exception e) {
			e.printStackTrace();
		}

		int start = new Random().nextInt(10000);
		for (int i = 0; i < 20; i++) {
			this.validNumbers.add(start + i);
		}
	}

	public synchronized boolean checkSerialNo(final int serialNo) {
		int index = this.validNumbers.indexOf(serialNo);

		if (index < 0) {
			return false;
		} else {
			this.validNumbers.remove(index);
			int max = this.validNumbers.get(this.validNumbers.size() - 1);
			this.validNumbers.add(max + 1);
			int min = this.validNumbers.get(0);

			if (max - min > 100) {
				this.validNumbers.remove(0);
				this.validNumbers.add(max + 2);
			}

			System.out.println(this.validNumbers);

			return true;
		}
	}

	/**
	 * @return algrithm
	 */
	public String getAlgrithm() {
		return algrithm;
	}

	public int getFirst() {
		return this.validNumbers.get(0);
	}

	/**
	 * @return mac
	 */
	public Mac getMac() {
		return mac;
	}

	/**
	 * @return secretKey
	 */
	public String getSecretKey() {
		return secretKey;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ClientConfig [algrithm=");
		builder.append(algrithm);
		builder.append(", secretKey=");
		builder.append(secretKey);
		builder.append(", mac=");
		builder.append(mac);
		builder.append(", validNumbers=");
		builder.append(validNumbers);
		builder.append("]");
		return builder.toString();
	}

}
