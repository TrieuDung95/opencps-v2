package org.opencps.api.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.opencps.api.resourcerole.model.DataSearchModel;
import org.opencps.api.resourcerole.model.ResourceRoleInputModel;
import org.opencps.api.resourceuser.model.ResourceUserInputModel;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.StringPool;

@Path("/resourceroles")
public interface ResourceRoleManagement {

	@GET
	@Path("/{className}/{classPK}")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getResourceRoles(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@DefaultValue(StringPool.BLANK) @PathParam("className") String className, @DefaultValue(StringPool.BLANK) @PathParam("classPK") String classPK,
			@BeanParam DataSearchModel query);

	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response create(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@BeanParam ResourceRoleInputModel input);

	@POST
	@Path("/update")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response createResourceRolePatch(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@BeanParam ResourceUserInputModel input, @FormParam("roles") String roles);
	
	@DELETE
	@Path("/{className}/{classPK}/{roleId}")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response delete(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@DefaultValue(StringPool.BLANK) @PathParam("className") String className, @DefaultValue(StringPool.BLANK) @PathParam("classPK") String classPK,
			@DefaultValue("0") @PathParam("roleId") long roleId);
	
	@GET
	@Path("/{className}/{classPK}/cloning/{sourcePK}")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response clone(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user, @Context ServiceContext serviceContext,
			@DefaultValue(StringPool.BLANK) @PathParam("className") String className, @DefaultValue(StringPool.BLANK) @PathParam("classPK") String classPK,
			@DefaultValue(StringPool.BLANK) @PathParam("sourcePK") String sourcePK);
}
