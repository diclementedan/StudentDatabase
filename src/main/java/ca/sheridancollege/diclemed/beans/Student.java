/* 
 *  Name: Dan Di Clemente
 */

package ca.sheridancollege.diclemed.beans;

import java.io.Serializable;

import lombok.*;

@Data
@NoArgsConstructor
public class Student implements Serializable{

	private Long id;
	private String name;
}
