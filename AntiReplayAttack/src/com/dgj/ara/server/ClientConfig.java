package com.dgj.ara.server;

import java.util.Random;
import java.util.Vector;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * 某客户端,只需要保存algorithm和secretKey这两个变量,其余可自动生成
 * @author xiaodu.email@gmail.com
 *
 */
public class ClientConfig {
	private final String algorithm;
	private final String secretKey;
	private Mac mac;
	private final Vector<Integer> validNumberArray = new Vector<Integer>();

	public ClientConfig(final String secrectKey, final String algorithm) {
		this.algorithm = algorithm;
		this.secretKey = secrectKey;

		try {
			SecretKeySpec keySpec = new SecretKeySpec(secrectKey.getBytes(), algorithm);
			this.mac = Mac.getInstance(algorithm);
			mac.init(keySpec);
		} catch (Exception e) {
			e.printStackTrace();
		}

		int start = new Random().nextInt(10000);
		for (int i = 0; i < 20; i++) {
			this.validNumberArray.add(start + i);
		}
	}

	/**
	 * 验证序列号是否在合法列表中,多线程情况下,该方法需要同步
	 * @author xiaodu.email@gmail.com
	 * @param serialNo
	 * @return
	 */
	public synchronized boolean checkSerialNo(final int serialNo) {
		int index = this.validNumberArray.indexOf(serialNo);

		if (index < 0) {
			return false;
		} else {
			this.validNumberArray.remove(index);
			int max = this.validNumberArray.get(this.validNumberArray.size() - 1);
			this.validNumberArray.add(max + 1);
			int min = this.validNumberArray.get(0);

			if (max - min > 100) {
				this.validNumberArray.remove(0);
				this.validNumberArray.add(max + 2);
			}

			System.out.println(this.validNumberArray);

			return true;
		}
	}

	/**
	 * @return algorithm
	 */
	public String getAlgorithm() {
		return algorithm;
	}

	public int getFirst() {
		return this.validNumberArray.get(0);
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
		builder.append(algorithm);
		builder.append(", secretKey=");
		builder.append(secretKey);
		builder.append(", mac=");
		builder.append(mac);
		builder.append(", validNumbers=");
		builder.append(validNumberArray);
		builder.append("]");
		return builder.toString();
	}

}
