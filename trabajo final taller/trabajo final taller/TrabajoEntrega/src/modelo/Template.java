package modelo;



import javax.persistence.*;


@Entity
public class Template {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "template_id")
	private Integer template_id;
	
	@Column(name = "carteleraUbicacion")
	private String carteleraUbicacion;
	
	@Column(name = "ordenadasPorCriterio")
	private String ordenadasPorCriterio;
	
	@Column(name="estado")
	private String estado;
	
	
	//CORRECCION_BIEN
	@ManyToOne(optional=false)
	@JoinColumn(name="usuario_id")
	private Usuario usrTemplate;
	
	public Template(){}
	
	
	public Template(String ubicacion,String criterio) {
		this.carteleraUbicacion=ubicacion;
		this.ordenadasPorCriterio=criterio;
	}


	public Integer getTemplate_id() {
		return template_id;
	}
	public void setTemplate_id(Integer id) {
		this.template_id = id;
	}
	public String getCarteleraUbicacion() {
		return carteleraUbicacion;
	}
	public void setCarteleraUbicacion(String carteleraUbicacion) {
		this.carteleraUbicacion = carteleraUbicacion;
	}
	public String getOrdenadasPorCriterio() {
		return ordenadasPorCriterio;
	}
	public void setOrdenadasPorCriterio(String ordenadasPorCriterio) {
		this.ordenadasPorCriterio = ordenadasPorCriterio;
	}
	

	@Override
	public String toString() {
		return "Template [template_id=" + template_id + ", carteleraUbicacion="
				+ carteleraUbicacion + ", ordenadasPorCriterio="
				+ ordenadasPorCriterio + "]";
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public Usuario getUsrTemplate() {
		return usrTemplate;
	}


	public void setUsrTemplate(Usuario usrTemplate) {
		this.usrTemplate = usrTemplate;
	}

}
