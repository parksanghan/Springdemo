    package com.example.demo.service;

    import com.example.demo.entity.Member;
    import com.example.demo.model.MemberModel;

    import com.example.demo.repository.MemberRepository;
    import lombok.Getter;
    import lombok.RequiredArgsConstructor;
    import lombok.Setter;
    import org.springframework.security.core.GrantedAuthority;
    import org.springframework.security.core.authority.SimpleGrantedAuthority;
    import org.springframework.security.core.userdetails.User;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.security.core.userdetails.UserDetailsService;
    import org.springframework.security.core.userdetails.UsernameNotFoundException;
    import org.springframework.stereotype.Service;

    import java.util.ArrayList;
    import java.util.Collection;
    import java.util.List;
    import java.util.Optional;

    @Service
    @RequiredArgsConstructor
    public class MyMemberDetailsService implements UserDetailsService {



        private final MemberRepository memberRepository;

        @Override
                         // 인자에는 유저가 제출한 username이 들어있습니다.
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Member member = memberRepository.findByuserName(username);
            if (member == null) {
                throw new UsernameNotFoundException(username);
            }
            List<GrantedAuthority> grantedAuthorites = new ArrayList<>();
            grantedAuthorites.add(new SimpleGrantedAuthority("ROLE_USER"));
            MemberModel user = new MemberModel(member.getUserName(),member.getPassWord(),grantedAuthorites);
            user.setDisplayName(member.getDisplayName());
            return user;

        }



    }









