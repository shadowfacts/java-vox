package net.shadowfacts.javavox.chunk;

import net.shadowfacts.javavox.util.SizeTrackingBuffer;

/**
 * @author shadowfacts
 */
public class VoxelChunk extends Chunk {

	private byte[][][] voxels = new byte[256][256][256];

	@Override
	public void read(SizeTrackingBuffer buffer, int childrenSize) {
		int numVoxels = buffer.getInt();

		for (int i = 0; i < numVoxels; i++) {
			byte x = buffer.getByte();
			byte y = buffer.getByte();
			byte z = buffer.getByte();
			byte colorIndex = buffer.getByte();

			voxels[x][y][z] = colorIndex;
		}
	}

}
