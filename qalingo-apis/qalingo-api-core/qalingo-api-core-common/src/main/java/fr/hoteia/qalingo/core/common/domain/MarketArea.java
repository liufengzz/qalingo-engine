/**
 * Most of the code in the Qalingo project is copyrighted Hoteia and licensed
 * under the Apache License Version 2.0 (release version ${license.version})
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *                   Copyright (c) Hoteia, 2012-2013
 * http://www.hoteia.com - http://twitter.com/hoteia - contact@hoteia.com
 *
 */
package fr.hoteia.qalingo.core.common.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name="TECO_MARKET_AREA")
public class MarketArea implements Serializable {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = -6237479836764154416L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID", nullable=false)
	private Long id;
	
	@Version
	@Column(name="VERSION", nullable=false, columnDefinition="int(11) default 1")
	private int version;

	@Column(name="NAME")
	private String name;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="CODE")
	private String code;
	
	@Column(name="IS_DEFAULT", nullable=false, columnDefinition="tinyint(1) default 0")
	private boolean isDefault;
	
	@Column(name="IS_ECOMMERCE", nullable=false, columnDefinition="tinyint(1) default 0")
	private boolean isEcommerce;
	
	@Column(name="THEME")
	private String theme;
	
	@Column(name="DOMAIN_NAME")
	private String domainName;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="VIRTUAL_CATALOG_ID")
	private CatalogVirtual virtualCatalog;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="MARKET_ID", insertable=false, updatable=false)
	private Market market;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="CURRENCY_ID", insertable=false, updatable=false)
	private CurrencyReferential currency;

	@ManyToOne
    @JoinColumn(name="DEFAULT_LOCALIZATION_ID", insertable=false, updatable=false)
	private Localization defaultLocalization;
	
	@ManyToMany(
			fetch = FetchType.EAGER,
	        targetEntity=fr.hoteia.qalingo.core.common.domain.Localization.class,
	        cascade={CascadeType.PERSIST, CascadeType.MERGE}
	    )
    @JoinTable(
	        name="TECO_MARKET_AREA_LOCALIZATION_REL",
	        joinColumns=@JoinColumn(name="MARKET_AREA_ID"),
	        inverseJoinColumns=@JoinColumn(name="LOCALIZATION_ID")
	    )
	private Set<Localization> localizations = new HashSet<Localization>(); 
	
	@ManyToMany(
			fetch = FetchType.EAGER,
	        targetEntity=fr.hoteia.qalingo.core.common.domain.Retailer.class,
	        cascade={CascadeType.PERSIST, CascadeType.MERGE}
	    )
    @JoinTable(
	        name="TECO_MARKET_AREA_RETAILER_REL",
	        joinColumns=@JoinColumn(name="MARKET_AREA_ID"),
	        inverseJoinColumns=@JoinColumn(name="RETAILER_ID")
	    )
	private Set<Retailer> retailers = new HashSet<Retailer>(); 
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_CREATE")
	private Date dateCreate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_UPDATE")
	private Date dateUpdate;

	public MarketArea(){
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public boolean isDefault() {
		return isDefault;
	}
	
	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}
	
	public boolean isEcommerce() {
		return isEcommerce;
	}

	public void setEcommerce(boolean isEcommerce) {
		this.isEcommerce = isEcommerce;
	}

	public String getTheme() {
		return theme;
	}
	
	public void setTheme(String theme) {
		this.theme = theme;
	}
	
	public String getDomainName() {
		return domainName;
	}
	
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public CatalogVirtual getVirtualCatalog() {
		return virtualCatalog;
	}
	
	public void setVirtualCatalog(CatalogVirtual virtualCatalog) {
		this.virtualCatalog = virtualCatalog;
	}
	
	public Market getMarket() {
		return market;
	}
	
	public void setMarket(Market market) {
		this.market = market;
	}
	
	public CurrencyReferential getCurrency() {
		return currency;
	}
	
	public void setCurrency(CurrencyReferential currency) {
		this.currency = currency;
	}
	
	public Localization getDefaultLocalization() {
		return defaultLocalization;
	}
	
	public void setDefaultLocalization(Localization defaultLocalization) {
		this.defaultLocalization = defaultLocalization;
	}
	
	public Set<Localization> getLocalizations() {
		return localizations;
	}
	
	public Localization getLocalization(String localeCode) {
		Localization localeToReturn = null;
		Set<Localization> locales = getLocalizations();
		if(locales != null
				&& locales.size() > 0){
			for (Iterator<Localization> iterator = locales.iterator(); iterator.hasNext();) {
				Localization localization = (Localization) iterator.next();
				if(localization.getLocaleCode().equalsIgnoreCase(localeCode)){
					localeToReturn = localization;
				}
			}
		}
		return localeToReturn;
	}
	
	public void setLocalizations(Set<Localization> localizations) {
		this.localizations = localizations;
	}
	
	public Set<Retailer> getRetailers() {
		return retailers;
	}
	
	public Retailer getDefaultRetailer() {
		Retailer defaultRetailer = null;
		Set<Retailer> retailers = getRetailers();
		if(retailers != null
				&& retailers.size() > 0){
			for (Iterator<Retailer> iterator = retailers.iterator(); iterator.hasNext();) {
				Retailer retailer = (Retailer) iterator.next();
				if(retailer.isDefault()){
					defaultRetailer = retailer;
				}
			}
			if(defaultRetailer == null){
				Iterator<Retailer> iterator = retailers.iterator();
				defaultRetailer = (Retailer) iterator.next();
			}
		}
		return defaultRetailer;
	}
	
	public Retailer getRetailer(String retailerCode) {
		Retailer retailerToReturn = null;
		Set<Retailer> retailers = getRetailers();
		if(retailers != null
				&& retailers.size() > 0){
			for (Iterator<Retailer> iterator = retailers.iterator(); iterator.hasNext();) {
				Retailer retailer = (Retailer) iterator.next();
				if(retailer.getCode().equalsIgnoreCase(retailerCode)){
					retailerToReturn = retailer;
				}
			}
		}
		return retailerToReturn;
	}
	
	public void setRetailers(Set<Retailer> retailers) {
		this.retailers = retailers;
	}
	
	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public Date getDateUpdate() {
		return dateUpdate;
	}

	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result
				+ ((dateCreate == null) ? 0 : dateCreate.hashCode());
		result = prime * result
				+ ((dateUpdate == null) ? 0 : dateUpdate.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((domainName == null) ? 0 : domainName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (isDefault ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((theme == null) ? 0 : theme.hashCode());
		result = prime * result + version;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MarketArea other = (MarketArea) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (dateCreate == null) {
			if (other.dateCreate != null)
				return false;
		} else if (!dateCreate.equals(other.dateCreate))
			return false;
		if (dateUpdate == null) {
			if (other.dateUpdate != null)
				return false;
		} else if (!dateUpdate.equals(other.dateUpdate))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (domainName == null) {
			if (other.domainName != null)
				return false;
		} else if (!domainName.equals(other.domainName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isDefault != other.isDefault)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (theme == null) {
			if (other.theme != null)
				return false;
		} else if (!theme.equals(other.theme))
			return false;
		if (version != other.version)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MarketArea [id=" + id + ", version=" + version + ", name="
				+ name + ", description=" + description + ", code=" + code
				+ ", isDefault=" + isDefault + ", theme=" + theme
				+ ", domainName=" + domainName + ", dateCreate=" + dateCreate
				+ ", dateUpdate=" + dateUpdate + "]";
	}

}