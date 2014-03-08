import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.faithfarm.sms.domain.CwtMaster;
import org.faithfarm.sms.domain.CwtModules;
import org.faithfarm.sms.domain.CwtProgram;
import org.faithfarm.sms.domain.CwtRoster;
import org.faithfarm.sms.domain.Intake;
import org.faithfarm.sms.domain.IpPermission;
import org.faithfarm.sms.hibernate.data.CwtModulesDao;
import org.faithfarm.sms.hibernate.data.CwtProgramDao;
import org.faithfarm.sms.hibernate.data.GenericDao;
import org.faithfarm.sms.hibernate.data.IntakeDao;

public class Test {

	public static void main(String[] args) {
		
		GenericDao dao = new GenericDao();
		List<IpPermission> list = dao.listAll(new IpPermission().getClass());
		for (int i=0;i<list.size();i++) {
			IpPermission obj = (IpPermission)list.get(i);
			System.out.println(">"+obj.getIpAddress());
		} 
		 
	}
}
