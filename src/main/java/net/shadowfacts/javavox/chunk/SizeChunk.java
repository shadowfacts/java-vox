package net.shadowfacts.javavox.chunk;

import net.shadowfacts.javavox.util.SizeTrackingBuffer;

/**
 * @author shadowfacts
 */
public class SizeChunk extends Chunk {

	private byte x;
	private byte y;
	private byte z;

	@Override
	public void read(SizeTrackingBuffer buffer, int childrenSize) {
		x = buffer.getByte();
		y = buffer.getByte();
		z = buffer.getByte();
	}
}
