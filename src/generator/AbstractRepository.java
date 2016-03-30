package generator;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * The Class AbstractRepository.
 */
public abstract class AbstractRepository {

	/**
	 * Serialize.
	 *
	 * @param path
	 *            the path
	 * @throws FileNotFoundException
	 *             the file not found exception
	 */
	public void serialize(String path) throws FileNotFoundException {
		XMLEncoder encoder = new XMLEncoder(new FileOutputStream(path));
		try {
			// serialisation de l'objet
			encoder.writeObject(this);
			encoder.flush();
		} finally {
			// fermeture de l'encodeur
			encoder.close();
		}
	}

	/**
	 * Deserialisation d'un objet depuis un fichier.
	 *
	 * @param fileName
	 *            the file name
	 * @return the object
	 * @throws FileNotFoundException
	 *             the file not found exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static Object decodeFromFile(String fileName)
			throws FileNotFoundException, IOException {
		Object object = null;
		// ouverture de decodeur
		XMLDecoder decoder = new XMLDecoder(new FileInputStream(fileName));
		try {
			// deserialisation de l'objet
			object = decoder.readObject();
		} finally {
			// fermeture du decodeur
			decoder.close();
		}
		return object;
	}

	/**
	 * Materialize.
	 *
	 * @param path
	 *            the path
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public abstract void materialize(String path) throws IOException;
}
