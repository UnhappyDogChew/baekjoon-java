#!/bin/bash

# add new class files, commit with proper message and push.

code_prop=`cat src/baekjoon.properties | grep problem.code`
code=${code_prop:13}

if [ ${#code} -eq 0 ]; then
	echo "오류: 문제 번호를 가져올 수 없음."
	exit 1
fi
commit_message="문제 $code 솔루션 추가"

echo "커밋 메세지: '$commit_message'"

git_log_check=`git log | grep "$commit_message"`
if [ ${#git_log_check} -ne 0 ]; then
	echo "오류: 이미 커밋된 문제.(문제코드: $code)"
	exit 2
fi


git add .
git commit -m "$commit_message"
git push

echo "문제 $code가 성공적으로 푸시됨."
