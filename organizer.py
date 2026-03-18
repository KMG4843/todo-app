import os
import sys
import argparse
import json
import shutil
from pathlib import Path

def organize_files(target_path):
    base_dir = Path(target_path)

    # 2.4. 예외 처리: 존재하지 않거나 디렉토리가 아닌 경우
    if not base_dir.exists() or not base_dir.is_dir():
        print(f"Error: '{target_path}'은(는) 유효한 디렉토리 경로가 아닙니다.")
        sys.exit(1)

    summary_data = {}

    # 디렉토리 내 모든 파일 탐색 (디렉토리는 제외)
    for file_path in [f for f in base_dir.iterdir() if f.is_file()]:
        # summary.json 자체는 분류에서 제외
        if file_path.name == "summary.json":
            continue

        # 2.4. 확장자가 없는 파일 처리
        extension = file_path.suffix.lower().replace('.', '')
        if not extension:
            extension = "no_extension"

        # 확장자 폴더 생성
        target_dir = base_dir / extension
        target_dir.mkdir(exist_ok=True)

        # 2.4. 동일 이름 파일 충돌 방지 로직
        destination = target_dir / file_path.name
        if destination.exists():
            counter = 1
            while destination.exists():
                destination = target_dir / f"{file_path.stem}_{counter}{file_path.suffix}"
                counter += 1

        # 파일 이동 및 요약 데이터 업데이트
        file_size = file_path.stat().st_size
        shutil.move(str(file_path), str(destination))

        if extension not in summary_data:
            summary_data[extension] = {"count": 0, "total_size_bytes": 0}
        
        summary_data[extension]["count"] += 1
        summary_data[extension]["total_size_bytes"] += file_size

    # 2.3. 결과 보고서 생성 (summary.json)
    with open(base_dir / "summary.json", "w", encoding="utf-8") as f:
        json.dump(summary_data, f, indent=4, ensure_ascii=False)

    print(f"분류 완료! 요약 리포트가 생성되었습니다: {base_dir / 'summary.json'}")

if __name__ == "__main__":
    # 2.1. CLI 인터페이스 설정
    parser = argparse.ArgumentParser(description="파일 확장자별 자동 분류기")
    parser.add_argument("--path", type=str, required=True, help="분류할 대상 디렉토리 경로")
    
    args = parser.parse_args()
    organize_files(args.path)