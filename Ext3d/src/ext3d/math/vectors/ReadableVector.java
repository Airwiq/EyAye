package ext3d.math.vectors;

import java.nio.FloatBuffer;

public interface ReadableVector {
	float length();

	float lengthSquared();

	Vector store(FloatBuffer var1);
}