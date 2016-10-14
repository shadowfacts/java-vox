package net.shadowfacts.javavox.chunk;

import net.shadowfacts.javavox.util.SizeTrackingBuffer;

/**
 * @author shadowfacts
 */
public abstract class Chunk {

	public static final int MAIN = ('M' << 24) | ('A' << 16) | ('I' << 8) | 'N';
	public static final int SIZE = ('S' << 24) | ('I' << 16) | ('Z' << 8) | 'E';
	public static final int VOXEL = ('X' << 24) | ('Y' << 16) | ('Z' << 8) | 'I';
	public static final int PALETTE = ('R' << 24) | ('G' << 16) | ('B' << 8) | 'A';

	protected Chunk[] children;

	public abstract void read(SizeTrackingBuffer buffer, int childrenSize);

}
