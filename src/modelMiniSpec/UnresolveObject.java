package modelMiniSpec;

import java.util.HashMap;

public interface UnresolveObject {
	public abstract MsType getUnresolvedType();
	public abstract void setResolvedType(MsType resolvedType);
	public default Boolean resolve(HashMap<String, MsType> typesDef, HashMap<String, MsEntity> entities){
		String idToResolve = getUnresolvedType().getTypeName();
		MsType resolvedType = typesDef.get(idToResolve);
		if (resolvedType == null){
			MsEntity entity = entities.get(idToResolve);
			if(entity==null)
				return false;
			resolvedType = new MsReference(entity);
		}
		setResolvedType(resolvedType);
		return true;
	}
}
