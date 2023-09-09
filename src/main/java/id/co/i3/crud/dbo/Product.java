package id.co.i3.crud.dbo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products")
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class Product
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    @Getter @Setter @Column(name = "id") private Integer id;

    @JsonProperty("version")
    @Getter @Setter @Column(name = "version") private String version;

    @JsonProperty("name")
    @Getter @Setter @Column(name = "name") private String name;

    @JsonProperty("price")
    @Getter @Setter @Column(name = "price") private Integer price;

    @JsonProperty("product_id")
    @Getter @Setter @Column(name = "product_id") private String productId;
}
