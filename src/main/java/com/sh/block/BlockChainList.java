package com.sh.block;

import java.util.ArrayList;
import java.util.Collection;

public class BlockChainList extends ArrayList<Block> {
	
	
	private static final long serialVersionUID = 2983146316536018529L;
	private static int DIFFICULT = 5;
	
	
	public BlockChainList() {
		super();
	}


	public BlockChainList(Collection<? extends Block> c) {
		super(c);
	}



	@Override
	public boolean add(Block e) {
		e.mineBlock(DIFFICULT);
		return super.add(e);
	}
	
}
