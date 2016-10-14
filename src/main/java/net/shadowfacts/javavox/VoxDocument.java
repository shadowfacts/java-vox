package net.shadowfacts.javavox;

import net.shadowfacts.javavox.chunk.Chunk;
import net.shadowfacts.javavox.chunk.MainChunk;
import net.shadowfacts.javavox.util.SizeTrackingBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * @author shadowfacts
 */
public class VoxDocument {

	public static final int MAGIC_NUM = ('V' << 24) | ('O' << 16) | ('X' << 8) | ' ';

	private int version;
	private MainChunk chunk;

	public VoxDocument(int version, MainChunk chunk) {
		this.version = version;
		this.chunk = chunk;
	}

	public static VoxDocument read(ByteBuffer buffer) {
		int magicNum = buffer.getInt();
		if (magicNum != MAGIC_NUM) {
			throw new RuntimeException(new IOException("Invalid header magic number"));
		}
		int version = buffer.getInt();
		return new VoxDocument(version, getMainChunk(buffer));
	}

	private static MainChunk getMainChunk(ByteBuffer buffer) {
		int id = buffer.getInt();
		if (id != Chunk.MAIN) {
			throw new RuntimeException(new IOException("Invalid main chunk id " + id));
		}
		int size = buffer.getInt();
		int childrenSize = buffer.getInt();
		MainChunk chunk = new MainChunk();
		chunk.read(new SizeTrackingBuffer(buffer, size), childrenSize);
		return chunk;
	}

}
