package ListAdder;

import MainData.Member;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MemberFileSystem {
    private static MemberFileSystem memberFileSystem = new MemberFileSystem();
    private ObservableList<Member> getMemberList = FXCollections.observableArrayList();

    public static MemberFileSystem getMemberFileSystem() {
        return memberFileSystem;
    }

    public ObservableList<Member> getGetMemberList() {
        return getMemberList;
    }

    public void addMembersToDatabase(Member member) {
        this.getMemberList.add(member);
    }
}

