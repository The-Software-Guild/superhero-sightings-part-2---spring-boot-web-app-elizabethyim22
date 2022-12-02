package com.sg.superhero_pt1.dao;

import com.sg.superhero_pt1.TestApplicationConfiguration;
import com.sg.superhero_pt1.model.Member;
import com.sg.superhero_pt1.model.MemberViewDetail;
import com.sg.superhero_pt1.model.Powers;
import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Test;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Before;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class MemberDaoDBTest extends TestCase {
    @Autowired
    MemberDao memberDao;

    @Autowired
    PowersDao powersDao;


    public MemberDaoDBTest(){}

    @Before
    public void setUp() {
        List<MemberViewDetail> members = memberDao.getAllMembers();
        for(MemberViewDetail member : members){
            memberDao.deleteMemberById(member.getMember_id());
        }
        List<Powers> powers = powersDao.getAllPowers();
        for(Powers power : powers) {
            powersDao.deletePowersById(power.getPowers_id());
        }
    }

    @Test
    public void testAddAndGetMember(){
        //add a power
        Powers powers = new Powers();
        powers.setPowers_name("Fly");
        powersDao.addPowers(powers);

        Member member = new Member();
        member.setMember_name("Test name");
        member.setDescription("Test description");
        member.setPowers_id(powers.getPowers_id());
        member = memberDao.addMember(member);
        Member fromDao = memberDao.getMemberById(member.getMember_id());
        assertEquals(member, fromDao);
    }

    @Test
    public void getAll(){
        //add a power
        Powers powers = new Powers();
        powers.setPowers_name("Fly");
        powersDao.addPowers(powers);

        Member member = new Member();
        member.setMember_name("Test name");
        member.setDescription("Test description");
        member.setPowers_id(powers.getPowers_id());
        memberDao.addMember(member);

        Member member2 = new Member();
        member2.setMember_name("Test name2");
        member2.setDescription("Test description2");
        member2.setPowers_id(powers.getPowers_id());
        memberDao.addMember(member2);

        List<Member> members = memberDao.getAll();
        assertEquals(2, members.size());
        assertTrue(members.contains(member));
        assertTrue(members.contains(member2));
    }

    @Test
    public void testUpdateMember() {
        //add a power
        Powers powers = new Powers();
        powers.setPowers_name("Fly");
        powersDao.addPowers(powers);

        Member member = new Member();
        member.setMember_name("Test name");
        member.setDescription("Test description");
        member.setPowers_id(powers.getPowers_id());
        member = memberDao.addMember(member);

        Member fromDao = memberDao.getMemberById(member.getMember_id());
        assertEquals(member, fromDao);
        member.setDescription("New Test Description");
        memberDao.updateMember(member);
        assertNotEquals(member, fromDao);
        fromDao = memberDao.getMemberById(member.getMember_id());
        assertEquals(member, fromDao);
    }

    @Test
    public void testDeleteMemberById(){
        //add a power
        Powers powers = new Powers();
        powers.setPowers_name("Fly");
        powersDao.addPowers(powers);

        Member member = new Member();
        member.setMember_name("Test name");
        member.setDescription("Test description");
        member.setPowers_id(powers.getPowers_id());
        member = memberDao.addMember(member);

        Member fromDao = memberDao.getMemberById(member.getMember_id());
        assertEquals(member, fromDao);
        memberDao.deleteMemberById(member.getMember_id());
        fromDao = memberDao.getMemberById(member.getMember_id());
        assertNull(fromDao);
    }

}