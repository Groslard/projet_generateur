package modelMiniSpec;

import java.util.HashMap;

/**
 * The Interface UnresolveObject.
 */
public interface UnresolveObject {

	/**
	 * Gets the unresolved type.
	 *
	 * @return the unresolved type
	 */
	public abstract MsType getUnresolvedType();

	/**
	 * Sets the resolved type.
	 *
	 * @param resolvedType
	 *            the new resolved type
	 */
	public abstract void setResolvedType(MsType resolvedType);

	/**
	 * Resolve.
	 *
	 * @param typesDef
	 *            the types def
	 * @param entities
	 *            the entities
	 * @return true if the types is resolved
	 */
	public default Boolean resolve(HashMap<String, MsType> typesDef,
			HashMap<String, MsEntity> entities) {
		String idToResolve = getUnresolvedType().getTypeName();
		MsType resolvedType = typesDef.get(idToResolve);
		if (resolvedType == null) {
			MsEntity entity = entities.get(idToResolve);
			if (entity == null)
				return false;
			resolvedType = new MsReference(entity);
		}
		setResolvedType(resolvedType);
		return true;
	}
}
