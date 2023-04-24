//import org.eclipse.swt.*;
//import org.eclipse.swt.events.*;
//import org.eclipse.swt.layout.*;
//import org.eclipse.swt.widgets.*;
//
//public class G {
//    public static void main(String[] args) {
//        Display d = Display.getDefault();
//        Shell s = new Shell(d);
//        new T(s, SWT.NONE);
//        s.setLayout(new FillLayout());
//        s.open();
//        s.pack();
//        while (!s.isDisposed()) {
//            if (!d.readAndDispatch()) {
//                d.sleep();
//            }
//        }
//        d.dispose();
//    }
//}
//
//class T extends Composite {
//    public T(Composite p, int style) {
//        super(p, style);
//        this.setLayout(new FillLayout());
//        final Button b = new Button(this, SWT.NONE);
//        b.setText("dd");
//        b.addSelectionListener(new SelectionAdapter() {
//            @Override
//            public void widgetSelected(SelectionEvent e) {
//                Shell s = b.getShell();
//                new R(s, SWT.NONE);
//                System.out.println("yes");
//                b.getParent().dispose();
//                s.layout();
//            }
//        });
//    }
//}
//
//class R extends Composite {
//    public R(Composite p, int style) {
//        super(p, style);
//        this.setLayout(new FillLayout());
//        final Button b = new Button(this, SWT.NONE);
//        b.setText("ee");
//        b.addSelectionListener(new SelectionAdapter() {
//            @Override
//            public void widgetSelected(SelectionEvent e) {
//                Shell s = b.getShell();
//                new T(s, SWT.NONE);
//                b.getParent().dispose();
//                s.layout();
//            }
//        });
//    }
//}