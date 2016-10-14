package net.shadowfacts.javavox.util;

import net.shadowfacts.javavox.chunk.*;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * @author shadowfacts
 */
public class SizeTrackingBuffer {

	private final ByteBuffer buffer;
	private final int maxSize;
	private int current = 0;

	public SizeTrackingBuffer(ByteBuffer buffer, int maxSize) {
		this.buffer = buffer;
		this.maxSize = maxSize;
	}

	public SizeTrackingBuffer(SizeTrackingBuffer buffer, int maxSize) {
		this(buffer.buffer, maxSize);
	}

	private void move(int amount) {
		current += amount;
		if (current > maxSize) {
			throw new RuntimeException(new IOException("Tried to read past maximum size"));
		}
	}

	public boolean atEnd() {
		return current >= maxSize;
	}

	public int getInt() {
		move(4);
		return buffer.getInt();
	}

	public byte getByte() {
		move(1);
		return buffer.get();
	}

	public Chunk getChunk() {
		int id = getInt();
		int size = getInt();
		int childrenSize = getInt();

		Chunk chunk;

		switch (id) {
			case (Chunk.SIZE):
				chunk = new SizeChunk();
				break;
			case (Chunk.VOXEL):
				chunk = new VoxelChunk();
				break;
			case (Chunk.PALETTE):
				chunk = new PaletteChunk();
				break;
			default:
				throw new RuntimeException(new IOException("Unknown id " + id));
		}

		chunk.read(new SizeTrackingBuffer(buffer, size), childrenSize);
		return chunk;
	}

}
