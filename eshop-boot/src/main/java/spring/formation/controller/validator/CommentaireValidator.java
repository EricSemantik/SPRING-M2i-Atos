package spring.formation.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.formation.model.Commentaire;

public class CommentaireValidator implements Validator{
	@Override
	public boolean supports(Class<?> clazz) {
		return Commentaire.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Commentaire commentaire = (Commentaire) target;
		
		if(!commentaire.getCommentaire().isEmpty() && commentaire.getCommentaire().length() < 30) {
			errors.rejectValue("commentaire", "commentaire.form.size30", "Le commentaire renseigné doit comporter au moins 30 caractères");
		}
	}
}
