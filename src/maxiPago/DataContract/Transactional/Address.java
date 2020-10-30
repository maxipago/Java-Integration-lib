package maxiPago.DataContract.Transactional;

public class Address {
	
	//Attributes
	private Integer id;
	private String name;
	private String address;
	private String address2;
	private String city;
	private String state;
	private String postalcode;
	private String country;
	private String phone;
	private String email;
	private String type;
	private String district;
	private String birthDate;
	
	//Getters and Setters 	
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	
	public String getBirthDate() {
		return birthDate;
	}
	
	public void setDistrict(String district) {
		this.district = district;
	}
	
	public String getDistrict() {
		return district;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalcode() {
		return this.postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
