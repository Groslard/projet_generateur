package modelMiniSpec;

import generator.JavaVisitor;
import generator.Visitor;

import java.util.ArrayList;

/**
 * The Class MsModel.
 */
public class MsModel {

	/** The name. */
	String name;

	/** The entities. */
	ArrayList<MsEntity> entities;

	/**
	 * Instantiates a new ms model.
	 *
	 * @param name
	 *            the name
	 */
	public MsModel(String name) {
		this.name = name;
		entities = new ArrayList<MsEntity>();
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Adds the entity.
	 *
	 * @param entity
	 *            the entity
	 */
	public void addEntity(MsEntity entity) {
		entities.add(entity);
	}

	/**
	 * Gets the entity.
	 *
	 * @param name
	 *            the name
	 * @return the entity
	 */
	public MsEntity getEntity(String name) {
		for (MsEntity entity : entities) {
			if (entity.name.equals(name))
				return entity;
		}
		return null;
	}

	/**
	 * Gets the entities.
	 *
	 * @return the entities
	 */
	public ArrayList<MsEntity> getEntities() {
		return entities;
	}

	/**
	 * Sets the entities.
	 *
	 * @param entities
	 *            the new entities
	 */
	public void setEntities(ArrayList<MsEntity> entities) {
		this.entities = entities;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String res = "MjPackage [name=" + name + ", \nentities=";
		for (MsEntity entity : entities)
			res += "\n" + entity;
		return res;
	}

	/**
	 * Accept.
	 *
	 * @param visitor
	 *            the visitor
	 */
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
