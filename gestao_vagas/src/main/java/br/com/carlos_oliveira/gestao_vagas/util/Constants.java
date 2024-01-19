package br.com.carlos_oliveira.gestao_vagas.util;

import lombok.experimental.UtilityClass;

//@NoArgsConstructor(access = AccessLevel.PRIVATE)
@UtilityClass
public final class Constants {
	public static final String APPLICATION_NAME = "Gestão de Vagas";

	public static final String HEADER_AUTHORIZATION = "Authorization";

	public static final String TOKEN_BEARER = "Bearer ";

	public static final String BLANK = "";

	/* FIELDS */

	public static final String CANDIDATE_ID = "candidate_id";

	public static final String COMPANY_ID = "company_id";

	/* URI */

	public static final String URI_CANDIDATE = "/candidate";

	public static final String URI_COMPANY = "/company";

	public static final String ROLES = "roles";

	public static final String ROLE_ = "ROLE_";
}
