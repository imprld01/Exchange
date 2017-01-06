package exchange.model.skill;

import java.sql.ResultSet;
import java.sql.SQLException;

import exchange.model.database.DataBaseAdmin;

public class Type {
	private Code typeCode;
	private String typeName;
	private Code kindCode;

	// 建構子()
	public Type() {
		this.typeCode = new Code("");
		this.kindCode = new Code("");
		setTypeName("");
	}

	// 建構子(Code,String,Code)
	public Type(Code typeCode, String typeName, Code kindCode) {
		this.typeCode = new Code(typeCode);
		this.kindCode = new Code(kindCode);
		setTypeName(typeName);
	}

	public Type(String typeName) throws SQLException {
		String query = "SELECT * FROM types WHERE type_name = '" + typeName + "'";
		ResultSet rs = DataBaseAdmin.selectDB(query);

		if (rs.next())
		{
			this.typeCode = new Code(rs.getString("type_code"));
			setTypeName(rs.getString("type_name"));
			this.kindCode = new Code(rs.getString("class_code"));
		}
	}
	
	// 建構子(Type)
	public Type(Type type) {
		this(type.getKindCode(), type.getTypeName(), type.getTypeCode());
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeName() {
		return typeName;
	}

	public Code getTypeCode() {
		return typeCode;
	}

	public Code getKindCode() {
		return kindCode;
	}



	@Override
	public String toString() {
		return "Type [typeCode=" + typeCode + ", typeName=" + typeName + ", kindCode=" + kindCode + "]";
	}
}
