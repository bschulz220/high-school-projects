package org.millardps.BruteForceCipherBenSchulz;

public class Runner {

	public static void main(String[] args) {
		Cipher julius = new Cipher();
		julius.getEncrypted();
		String message = julius.getEncrypted();
		CipherSolver brutus = new CipherSolver(message);
	}

}
