package database;

public class hash {
	public static String getHash(String plainText, String hashType) {
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance(hashType);
			byte[] byteArray = md.digest(plainText.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int x = 0; x < byteArray.length; ++x) {
				sb.append(Integer.toHexString((byteArray[x] & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();

		} catch (java.security.NoSuchAlgorithmException e) {
		}
		return null;
	}

	public static String sha1(String msg) {
		return hash.getHash(msg, "SHA1");
	}
}
