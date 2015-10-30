package clickableconsole;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.IHyperlink;
import org.eclipse.ui.ide.IDE;

public class SetUppableIHyperlink implements IHyperlink {
	
	
	final String m_absoluteFilePath;
	final int m_lineNumber;
	
	public SetUppableIHyperlink(String absoluteFilePath, int lineNumber) {
		m_absoluteFilePath = absoluteFilePath;
		m_lineNumber = lineNumber;
	}

	@Override
	public void linkExited()
	{
	}

	@Override
	public void linkEntered()
	{
	}

	@Override
	public void linkActivated()
	{
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		try
		{
			Path absPath = Paths.get(m_absoluteFilePath);
			IEditorPart editorPart = IDE.openEditorOnFileStore(page, EFS.getStore(absPath.toUri()));
			ClickablePatternMatchListenerDelegate.goToLine(editorPart, m_lineNumber);
		}
		catch (Exception exception)
		{
			throw new RuntimeException(exception);
		}
	}
}

