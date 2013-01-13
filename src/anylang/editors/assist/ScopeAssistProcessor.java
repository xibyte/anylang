package anylang.editors.assist;

import mlang.dom.DOM;
import mlang.dom.DOMProvider;
import mlang.utils.LazyList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;
import org.eclipselabs.mlang.models.soy.Doclet;
import org.eclipselabs.mlang.models.soy.Param;
import org.eclipselabs.mlang.models.soy.Tag;
import org.eclipselabs.mlang.models.soy.TemplateTag;


public class ScopeAssistProcessor implements IContentAssistProcessor {

	private final DOMProvider astProvider;
	
	public ScopeAssistProcessor(DOMProvider astProvider) {
		this.astProvider = astProvider;
	}

	private EObject getNode(int offset) {
		DOM ast = astProvider.getDOM();
		if (ast == null) {
			return null;
		}
		return ast.find(offset);
	}

	@Override
	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer,
			int offset) {
/*

		((IDocumentExtension3)viewer.getDocument())
		.computePartitioning(Html.JS_PARTITIONING, 0, viewer.getDocument().getLength(), false);
*/		
		LazyList<ICompletionProposal> proposals = new LazyList<ICompletionProposal>();
		
		EObject node = getNode(offset);
		if (node instanceof Tag) {
			Tag tag = (Tag) node;
			while (tag != null) {
				
				if (tag instanceof TemplateTag) {
					Doclet doclet = ((TemplateTag) tag).getDoclet();
					if (doclet != null) {
						for (Param param : doclet.getParams()) {
							String rplcmnt = param.getName();
							int length = rplcmnt.length();
							CompletionProposal proposal = new CompletionProposal(rplcmnt, offset, 0, length);
//						viewer.getDocument().get(offset, 50);
							proposals.add(proposal);
						}
					}
					break;
				}
				tag = tag.getParent();
			}
		}
		
		
		
		return proposals.toArray(new ICompletionProposal[proposals.size()]);
	}

	@Override
	public IContextInformation[] computeContextInformation(ITextViewer viewer,
			int offset) {
		return null;
	}

	@Override
	public char[] getCompletionProposalAutoActivationCharacters() {
		return null;
	}

	@Override
	public char[] getContextInformationAutoActivationCharacters() {
		return null;
	}

	@Override
	public String getErrorMessage() {
		return null;
	}

	@Override
	public IContextInformationValidator getContextInformationValidator() {
		return null;
	}

}
