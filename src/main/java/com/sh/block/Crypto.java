package com.sh.block;


import java.security.MessageDigest;
import java.util.List;


public class Crypto {
	
	public static String createHash(String input) {
		
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(input.getBytes("UTF-8"));
			StringBuilder hexString = new StringBuilder();
			for(int i=0;i<hash.length;i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if(hex.length() == 1) {hexString.append('0');}
				hexString.append(hex);
			}
			return hexString.toString();
			
		} catch (Exception e) {
			
			throw new RuntimeException(e);
		}
		
	}
	
	public static boolean validateChain(List<Block> blockchain) {
		
		for(int i=1;i<blockchain.size();i++) {
			Block current = blockchain.get(i);
			Block previous = blockchain.get(i-1);
			
			if(!current.getHash().equals(current.calculateHash())) {
				System.out.println("Current hashes not equals");
				return false;
			}
			
			if(!previous.getHash().equals(current.getPreviousHash())) {
				System.out.println("Previous hashes not equals");
				return false;
			}
			
		}
		return true;
		
	}
	
	
	
}
